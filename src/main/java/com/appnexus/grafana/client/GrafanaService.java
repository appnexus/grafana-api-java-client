/* Licensed under Apache-2.0 */
package com.appnexus.grafana.client;

import com.appnexus.grafana.client.models.*;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.*;

import javax.xml.crypto.Data;

public interface GrafanaService {
  String GRAFANA_DASHBOARDS = "api/dashboards/db/";
  String GRAFANA_NOTIFICATIONS = "api/alert-notifications/";
  String GRAFANA_ALERTS = "api/alerts/";
  String GRAFANA_SEARCH = "api/search/";
  String GRAFANA_DATASOURCES = "api/datasources/";

  String AUTHORIZATION = "Authorization";

  //Dashboards
  @GET(GRAFANA_DASHBOARDS + "{dashboard}")
  Call<GrafanaDashboard> getDashboard(
      @Header(AUTHORIZATION) String authorization, @Path("dashboard") String dashboard);

  @POST(GRAFANA_DASHBOARDS)
  Call<DashboardMeta> postDashboard(
      @Header(AUTHORIZATION) String authorization, @Body GrafanaDashboard dashboard);

  @DELETE(GRAFANA_DASHBOARDS + "{dashboard}")
  Call<DashboardSuccessfulDelete> deleteDashboard(
      @Header(AUTHORIZATION) String authorization, @Path("dashboard") String dashboard);

  //Notifications
  @GET(GRAFANA_NOTIFICATIONS + "{id}")
  Call<AlertNotification> getNotification(
      @Header(AUTHORIZATION) String authorization, @Path("id") Integer id);

  @GET(GRAFANA_NOTIFICATIONS)
  Call<List<AlertNotification>> getNotifications(@Header(AUTHORIZATION) String authorization);

  @POST(GRAFANA_NOTIFICATIONS)
  Call<AlertNotification> postNotification(
      @Header(AUTHORIZATION) String authorization, @Body AlertNotification notification);

  @PUT(GRAFANA_NOTIFICATIONS + "{id}")
  Call<AlertNotification> putNotification(
      @Header(AUTHORIZATION) String authorization,
      @Path("id") Integer id,
      @Body AlertNotification notification);

  @DELETE(GRAFANA_NOTIFICATIONS + "{id}")
  Call<GrafanaMessage> deleteNotification(
      @Header(AUTHORIZATION) String authorization, @Path("id") Integer id);

  //Alerts
  @GET(GRAFANA_ALERTS + "{id}")
  Call<DashboardPanelAlert> getAlert(
      @Header(AUTHORIZATION) String authorization, @Path("id") Integer id);

  // Search
  @GET(GRAFANA_SEARCH)
  Call<List<GrafanaSearchResult>> search(
      @Header(AUTHORIZATION) String authorization,
      @Query("query") String query,
      @Query("tag") String tag,
      @Query("starred") Boolean starred,
      @Query("tagcloud") Boolean tagcloud);

  // Datasources
  @GET(GRAFANA_DATASOURCES)
  Call<List<Datasource>> getDataSources(@Header(AUTHORIZATION) String authorization);

  @POST(GRAFANA_DATASOURCES)
  Call<DatasourceCreationResult> createDataSource(
      @Header(AUTHORIZATION) String authorization, @Body Datasource ds);

  @PUT(GRAFANA_DATASOURCES + "{id}")
  Call<DatasourceCreationResult> updateDatasource(
          @Header(AUTHORIZATION) String authorization, @Body Datasource ds, @Path("id") int id);

  @GET(GRAFANA_DATASOURCES + "id/{name}")
  Call<DatasourceIdResult> getDatasourceIdbyName(@Header(AUTHORIZATION) String authorization, @Path("name") String name);

  @GET(GRAFANA_DATASOURCES + "{id}")
  Call<Datasource> getDataSource(@Header(AUTHORIZATION) String authorization, @Path("id") int id);

  @GET(GRAFANA_DATASOURCES + "name/{name}")
  Call<Datasource> getDataSource(
      @Header(AUTHORIZATION) String authorization, @Path("name") String name);

  @DELETE(GRAFANA_DATASOURCES + "{id}")
  Call<GrafanaMessage> deleteDatasource(@Header(AUTHORIZATION) String authorization, @Path("id") int id);
}
