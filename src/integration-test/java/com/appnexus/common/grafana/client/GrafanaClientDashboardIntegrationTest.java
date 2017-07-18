/* Licensed under Apache-2.0 */
package com.appnexus.common.grafana.client;

import com.appnexus.common.grafana.client.models.AlertNotification;
import com.appnexus.common.grafana.client.models.Dashboard;
import com.appnexus.common.grafana.client.models.DashboardMeta;
import com.appnexus.common.grafana.client.models.DashboardPanel;
import com.appnexus.common.grafana.client.models.DashboardPanelAlert;
import com.appnexus.common.grafana.client.models.DashboardPanelAlertCondition;
import com.appnexus.common.grafana.client.models.DashboardPanelAlertConditionEvaluator;
import com.appnexus.common.grafana.client.models.DashboardPanelAlertConditionOperator;
import com.appnexus.common.grafana.client.models.DashboardPanelAlertConditionQuery;
import com.appnexus.common.grafana.client.models.DashboardPanelAlertConditionReducer;
import com.appnexus.common.grafana.client.models.DashboardPanelTarget;
import com.appnexus.common.grafana.client.models.DashboardPanelXAxis;
import com.appnexus.common.grafana.client.models.DashboardPanelYAxis;
import com.appnexus.common.grafana.client.models.DashboardRow;
import com.appnexus.common.grafana.client.models.GrafanaDashboard;
import com.appnexus.common.grafana.configuration.GrafanaConfiguration;
import com.appnexus.common.grafana.exceptions.GrafanaException;
import org.junit.After;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/** To run this integration test a valid API key, host and data source must be added */
public class GrafanaClientDashboardIntegrationTest {

  private static final String GRAFANA_API_KEY = ""; //Replace with valid API key
  private static final String GRAFANA_HOST = ""; // Replace with valid host
  private static final String DASHBOARD_DATA_SOURCE = ""; // Replace with valid data source

  private static final String DASHBOARD_NAME = "integration-test-dashboard";

  private GrafanaConfiguration grafanaConfiguration =
      new GrafanaConfiguration().host(GRAFANA_HOST).apiKey(GRAFANA_API_KEY);
  private GrafanaClient grafanaClient;

  @Before
  public void before() throws Exception {
    grafanaClient = new GrafanaClient(grafanaConfiguration);
    deleteDashboardTest(DASHBOARD_NAME, false);
  }

  @After
  public void after() throws Exception {
    deleteDashboardTest(DASHBOARD_NAME, false);
  }

  //DASHBOARDS

  private DashboardMeta createDashboardTest(GrafanaDashboard grafanaDashboard)
      throws GrafanaException, IOException {
    DashboardMeta dashboardMeta = grafanaClient.createDashboard(grafanaDashboard);

    assert dashboardMeta.slug().equals(grafanaDashboard.meta().slug());

    return dashboardMeta;
  }

  private void getDashboardTest(String dashboardName) throws GrafanaException, IOException {
    GrafanaDashboard dashboard = grafanaClient.getDashboard(dashboardName);

    assert dashboard.meta().slug().equals(dashboardName);
  }

  private void deleteDashboardTest(String dashboardName, Boolean shouldAssert)
      throws GrafanaException, IOException {
    String result;

    try {
      result = grafanaClient.deleteDashboard(dashboardName);
      assert result.contains(dashboardName);
    } catch (GrafanaException e) {
      //Grafana only returns the dashboard name if the dashboard existed and was deleted
      if (shouldAssert) {
        fail("Could not delete dashboard " + dashboardName);
      }
    }
  }

  private void getDeletedDashboardTest(String dashboardName) throws IOException {
    try {
      grafanaClient.getDashboard(dashboardName);
    } catch (GrafanaException e) {
      assert e.getMessage().equals("Dashboard " + dashboardName + " does not exist");
    }
  }

  @Test
  public void dashboardTest() throws GrafanaException, IOException {
    Dashboard dashboard = new Dashboard().title(DASHBOARD_NAME).schemaVersion(1);

    DashboardMeta dashboardMeta = new DashboardMeta().canSave(true).slug(DASHBOARD_NAME);

    GrafanaDashboard grafanaDashboard =
        new GrafanaDashboard().meta(dashboardMeta).dashboard(dashboard);

    //create new dashboard
    DashboardMeta createdDashboardMeta = createDashboardTest(grafanaDashboard);
    //read dashboard
    getDashboardTest(createdDashboardMeta.slug());
    //delete dashboard
    deleteDashboardTest(createdDashboardMeta.slug(), true);
    //confirm dashboard has been deleted
    getDeletedDashboardTest(createdDashboardMeta.slug());
  }

  // ALERTS

  @Test
  public void alertTest() throws GrafanaException, IOException {
    String dashboardName = DASHBOARD_NAME;

    String alertName = "test alert";

    String targetRefId = "getSomeMetric";

    DashboardPanelAlertConditionOperator dashboardPanelAlertConditionOperator =
        new DashboardPanelAlertConditionOperator()
            .type(DashboardPanelAlertConditionOperator.Type.AND);

    DashboardPanelAlertConditionEvaluator dashboardPanelAlertConditionEvaluator =
        new DashboardPanelAlertConditionEvaluator()
            .type(DashboardPanelAlertConditionEvaluator.Type.GREATER_THAN)
            .params(Collections.singletonList(9999999.0));

    DashboardPanelAlertConditionReducer dashboardPanelAlertConditionReducer =
        new DashboardPanelAlertConditionReducer()
            .type(DashboardPanelAlertConditionReducer.Type.AVG)
            .params(new ArrayList<>());

    DashboardPanelTarget dashboardPanelTarget =
        new DashboardPanelTarget().refId(targetRefId).target("*");

    DashboardPanelAlertConditionQuery dashboardPanelAlertConditionQuery =
        new DashboardPanelAlertConditionQuery()
            .params(new ArrayList<>(Arrays.asList(targetRefId, "5m", "now")))
            .model(dashboardPanelTarget);

    DashboardPanelAlertCondition dashboardPanelAlertCondition =
        new DashboardPanelAlertCondition()
            .type(DashboardPanelAlertCondition.Type.QUERY)
            .query(dashboardPanelAlertConditionQuery)
            .reducer(dashboardPanelAlertConditionReducer)
            .evaluator(dashboardPanelAlertConditionEvaluator)
            .operator(dashboardPanelAlertConditionOperator);

    AlertNotification alertNotification = new AlertNotification().id(1);

    DashboardPanelAlert dashboardPanelAlert =
        new DashboardPanelAlert()
            .name(alertName)
            .conditions(new ArrayList<>(Collections.singletonList(dashboardPanelAlertCondition)))
            .noDataState(DashboardPanelAlert.AlertState.NO_DATA)
            .executionErrorState(DashboardPanelAlert.AlertState.ALERTING)
            .frequency("60s")
            .handler(1)
            .notifications(new ArrayList<>(Collections.singletonList(alertNotification)));

    DashboardPanelXAxis dashboardPanelXAxis =
        new DashboardPanelXAxis().show(true).mode(DashboardPanelXAxis.Mode.TIME);

    DashboardPanelYAxis dashboardPanelYAxis =
        new DashboardPanelYAxis().format(DashboardPanelYAxis.Format.SHORT).logBase(1).show(true);

    //Datasource is required or alerts cannot be added
    DashboardPanel dashboardPanel =
        new DashboardPanel()
            .alert(dashboardPanelAlert)
            .targets(new ArrayList<>(Collections.singletonList(dashboardPanelTarget)))
            .datasource(DASHBOARD_DATA_SOURCE)
            .type(DashboardPanel.Type.GRAPH)
            .fill(1)
            .title(dashboardName)
            .linewidth(1)
            .lines(true)
            .height("300px")
            .span(12)
            .xaxis(dashboardPanelXAxis)
            .yaxes(new ArrayList<>(Arrays.asList(dashboardPanelYAxis, dashboardPanelYAxis)));

    DashboardRow dashboardRow =
        new DashboardRow()
            .collapse(false)
            .panels(new ArrayList<>(Collections.singletonList(dashboardPanel)));

    Dashboard dashboard =
        new Dashboard()
            .title(dashboardName)
            .schemaVersion(1)
            .rows(new ArrayList<>(Collections.singletonList(dashboardRow)));

    DashboardMeta dashboardMeta = new DashboardMeta().canSave(true).slug(dashboardName);
    GrafanaDashboard grafanaDashboard =
        new GrafanaDashboard().meta(dashboardMeta).dashboard(dashboard);

    //create new dashboard
    DashboardMeta createdDashboardMeta = createDashboardTest(grafanaDashboard);
    //get new dashboard
    GrafanaDashboard newDashboard = grafanaClient.getDashboard(createdDashboardMeta.slug());

    //make sure alert was created
    assert newDashboard.dashboard().rows().get(0).panels().get(0).alert().name().equals(alertName);
  }
}
