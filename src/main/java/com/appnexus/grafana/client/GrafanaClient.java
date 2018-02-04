/* Licensed under Apache-2.0 */
package com.appnexus.grafana.client;

import static java.net.HttpURLConnection.HTTP_NOT_FOUND;

import com.appnexus.grafana.client.models.*;
import com.appnexus.grafana.configuration.GrafanaConfiguration;
import com.appnexus.grafana.exceptions.GrafanaDashboardCouldNotDeleteException;
import com.appnexus.grafana.exceptions.GrafanaDashboardDoesNotExistException;
import com.appnexus.grafana.exceptions.GrafanaException;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class GrafanaClient {

  private final String host;
  private final String apiKey;
  private final GrafanaService service;

  private static final ObjectMapper mapper =
      new ObjectMapper()
          .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
          .setSerializationInclusion(JsonInclude.Include.NON_NULL)
          .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

  /** @param configuration the information needed to communicate with Grafana. */
  public GrafanaClient(GrafanaConfiguration configuration) {
    this(configuration, new OkHttpClient());
  }

  /**
   * Allows for the user to provide an OkHttpClient that can be used to connect to the Grafana
   * service. The client provided can be configured with an {@link okhttp3.Interceptor} or other
   * specifics that are then used during communications with Grafana
   *
   * @param configuration the information needed to communicate with Grafana.
   * @param client An OkHttpClient that is used to connect to Grafana
   */
  public GrafanaClient(GrafanaConfiguration configuration, OkHttpClient client) {
    this.apiKey = configuration.apiKey();
    this.host = configuration.host();

    Retrofit retrofit =
        new Retrofit.Builder()
            .baseUrl(host)
            .client(client)
            .addConverterFactory(JacksonConverterFactory.create(mapper))
            .build();

    service = retrofit.create(GrafanaService.class);
  }

  public String getHost() {
    return host;
  }

  /**
   * Searches for an existing dashboard by name.
   *
   * @param dashboardName the name of the dashboard to search for.
   * @return {@link GrafanaDashboard} with matching name.
   * @throws GrafanaDashboardDoesNotExistException if a dashboard with matching name does not exist.
   * @throws GrafanaException if Grafana returns an error when trying to retrieve the dashboard.
   * @throws IOException if a problem occurred talking to the server.
   */
  public GrafanaDashboard getDashboard(String dashboardName)
      throws GrafanaDashboardDoesNotExistException, GrafanaException, IOException {

    Response<GrafanaDashboard> response = service.getDashboard(apiKey, dashboardName).execute();

    if (response.isSuccessful()) {
      return response.body();
    } else if (response.code() == HTTP_NOT_FOUND) {
      throw new GrafanaDashboardDoesNotExistException(
          "Dashboard " + dashboardName + " does not exist");
    } else {
      throw GrafanaException.withErrorBody(response.errorBody());
    }
  }

  /**
   * Creates a Grafana dashboard. If a dashboard already exists with the same name it will be
   * updated.
   *
   * @param grafanaDashboard the dashboard to be created.
   * @return Meta information about the newly created dashboard
   * @throws GrafanaException if Grafana returns an error when trying to create the dashboard.
   * @throws IOException if a problem occurred talking to the server.
   */
  public DashboardMeta createDashboard(GrafanaDashboard grafanaDashboard)
      throws GrafanaException, IOException {
    return updateDashboard(grafanaDashboard);
  }

  /**
   * Updates a Grafana dashboard. If the dashboard did not previously exist it will be created.
   *
   * @param dashboard the dashboard to be updated.
   * @return Meta information about the updated dashboard
   * @throws GrafanaException if Grafana returns an error when trying to create the dashboard.
   * @throws IOException if a problem occurred talking to the server.
   */
  public DashboardMeta updateDashboard(GrafanaDashboard dashboard)
      throws GrafanaException, IOException {

    Response<DashboardMeta> response = service.postDashboard(apiKey, dashboard).execute();

    if (response.isSuccessful()) {
      return response.body();
    } else {
      throw GrafanaException.withErrorBody(response.errorBody());
    }
  }

  /**
   * Deletes Grafana dashboard by name.
   *
   * @param dashboardName the name of the dashboard to delete.
   * @return The name of the deleted dashboard.
   * @throws GrafanaDashboardDoesNotExistException if the dashboard does not exist
   * @throws GrafanaDashboardCouldNotDeleteException if Grafana returns an error when trying to
   *     delete the dashboard.
   * @throws IOException if a problem occurred talking to the server.
   */
  public String deleteDashboard(String dashboardName)
      throws GrafanaDashboardDoesNotExistException, GrafanaDashboardCouldNotDeleteException,
          IOException {

    Response<DashboardSuccessfulDelete> response =
        service.deleteDashboard(apiKey, dashboardName).execute();

    if (response.isSuccessful()) {
      return response.body().title();
    } else if (response.code() == HTTP_NOT_FOUND) {
      throw new GrafanaDashboardDoesNotExistException(
          "Dashboard " + dashboardName + " does not exist");
    } else {
      throw GrafanaDashboardCouldNotDeleteException.withErrorBody(response.errorBody());
    }
  }

  /**
   * Searches for a Grafana notification with a given id.
   *
   * @param id the id of the notification to search for.
   * @return the notification with the given id.
   * @throws GrafanaException if a notification with the given id does not exist.
   * @throws IOException if a problem occurred talking to the server.
   */
  public AlertNotification getNotification(Integer id) throws GrafanaException, IOException {

    Response<AlertNotification> response = service.getNotification(apiKey, id).execute();

    if (response.isSuccessful()) {
      return response.body();
    } else if (response.code() == HTTP_NOT_FOUND) {
      throw new GrafanaException("Notification" + id + " does not exist");
    } else {
      throw GrafanaException.withErrorBody(response.errorBody());
    }
  }

  /**
   * Returns a list of all Grafana notifications.
   *
   * @return a list of notifications.
   * @throws GrafanaException if Grafana returns an unexpected error.
   * @throws IOException if a problem occurred talking to the server.
   */
  public List<AlertNotification> getNotifications() throws GrafanaException, IOException {

    Response<List<AlertNotification>> response = service.getNotifications(apiKey).execute();

    if (response.isSuccessful()) {
      return response.body();
    } else {
      throw GrafanaException.withErrorBody(response.errorBody());
    }
  }

  /**
   * Creates a Grafana alert notification.
   *
   * @param alertNotification the notification to be created.
   * @return the newly created notification
   * @throws GrafanaException if Grafana returns an error when trying to create the notification.
   * @throws IOException if a problem occurred talking to the server.
   */
  public AlertNotification createNotification(AlertNotification alertNotification)
      throws GrafanaException, IOException {

    Response<AlertNotification> response =
        service.postNotification(apiKey, alertNotification).execute();

    if (response.isSuccessful()) {
      return response.body();
    } else {
      throw GrafanaException.withErrorBody(response.errorBody());
    }
  }

  /**
   * Updates a Grafana alert notification by id.
   *
   * @param id the id of the notification to be updated.
   * @param alertNotification the notification values to be updated.
   * @return the newly updated notification
   * @throws GrafanaException if a notification does not exist for the given id.
   * @throws IOException if a problem occurred talking to the server.
   */
  public AlertNotification updateNotification(Integer id, AlertNotification alertNotification)
      throws GrafanaException, IOException {

    Response<AlertNotification> response =
        service.putNotification(apiKey, id, alertNotification).execute();

    if (response.isSuccessful()) {
      return response.body();
    } else if (response.code() == HTTP_NOT_FOUND) {
      throw new GrafanaException("Notification id: " + id + " does not exist");
    } else {
      throw GrafanaException.withErrorBody(response.errorBody());
    }
  }

  /**
   * Deletes Grafana notification by id.
   *
   * @param id the id of the notification to delete.
   * @return confirmation of the deleted notification.
   * @throws GrafanaException if Grafana returns an error when trying to delete the notification.
   * @throws IOException if a problem occurred talking to the server.
   */
  public String deleteNotification(Integer id) throws GrafanaException, IOException {

    Response<GrafanaMessage> response = service.deleteNotification(apiKey, id).execute();

    if (response.isSuccessful()) {
      return response.body().message();
    } else {
      throw GrafanaException.withErrorBody(response.errorBody());
    }
  }

  /**
   * Searches for a Grafana alert with a given id.
   *
   * @param id the id of the alert to search for.
   * @return the alert with the given id.
   * @throws GrafanaException if Grafana returns an error when trying to find the alert.
   * @throws IOException if a problem occurred talking to the server.
   */
  public DashboardPanelAlert getAlert(Integer id) throws GrafanaException, IOException {

    Response<DashboardPanelAlert> response = service.getAlert(apiKey, id).execute();

    if (response.isSuccessful()) {
      return response.body();
    } else {
      throw GrafanaException.withErrorBody(response.errorBody());
    }
  }

  public List<GrafanaSearchResult> search(
      String query, String tag, Boolean starred, Boolean tagcloud)
      throws GrafanaException, IOException {

    Response<List<GrafanaSearchResult>> response =
        service.search(apiKey, query, tag, starred, tagcloud).execute();

    if (response.isSuccessful()) {
      return response.body();
    } else {
      throw GrafanaException.withErrorBody(response.errorBody());
    }
  }

  public Datasource getDatasource(int id) throws GrafanaException, IOException {
    Response<Datasource> response = service.getDataSource(apiKey, id).execute();
    if (response.isSuccessful()) {
      return response.body();
    } else {
      throw GrafanaException.withErrorBody(response.errorBody());
    }
  }

  public Datasource getDatasource(String name) throws GrafanaException, IOException {
    Response<Datasource> response = service.getDataSource(apiKey, name).execute();
    if (response.isSuccessful()) {
      return response.body();
    } else {
      throw GrafanaException.withErrorBody(response.errorBody());
    }
  }

  public List<Datasource> getDatasources() throws GrafanaException, IOException {
    Response<List<Datasource>> response = service.getDataSources(apiKey).execute();
    if (response.isSuccessful()) {
      return response.body();
    } else {
      throw GrafanaException.withErrorBody(response.errorBody());
    }
  }

  public DatasourceCreationResult createDatasource(Datasource ds) throws GrafanaException, IOException {
    Response<DatasourceCreationResult> response = service.createDataSource(apiKey, ds).execute();
    if (response.isSuccessful()) {
      return response.body();
    } else {
      throw GrafanaException.withErrorBody(response.errorBody());
    }
  }
}
