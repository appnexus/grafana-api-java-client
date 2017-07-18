/* Licensed under Apache-2.0 */
package com.appnexus.grafana.client;

import com.appnexus.grafana.client.models.AlertNotification;
import com.appnexus.grafana.client.models.DashboardMeta;
import com.appnexus.grafana.client.models.DashboardPanelAlert;
import com.appnexus.grafana.client.models.DashboardSuccessfulDelete;
import com.appnexus.grafana.client.models.GrafanaDashboard;
import com.appnexus.grafana.client.models.GrafanaMessage;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface GrafanaService {
  String GRAFANA_DASHBOARDS = "api/dashboards/db/";
  String GRAFANA_NOTIFICATIONS = "api/alert-notifications/";
  String GRAFANA_ALERTS = "api/alerts/";

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
}
