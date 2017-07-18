# Grafana Client

A simple java client for interacting with [Grafana](http://docs.grafana.org/) using a 
fluent interface.


## Usage

Simple Example:
```
import com.appnexus.wsp.grafana.client.GrafanaClient;

//Setup the client
GrafanaConfiguration grafanaConfiguration =
        new GrafanaConfiguration().host("your_grafana_host").apiKey("Bearer your_secret_key");
GrafanaClient grafanaClient = new GrafanaClient(grafanaConfiguration);

//Setup the dashboard
String DASHBOARD_NAME = "new_dashboard";

Dashboard dashboard = new Dashboard()
      .title(DASHBOARD_NAME)
      .version(0);

GrafanaDashboard grafanaDashboard = new GrafanaDashboard().dashboard(dashboard);

//Make API calls
grafanaClient.createDashboard(grafanaDashboard);

grafanaClient.getDashboard(DASHBOARD_NAME);

grafanaClient.deleteDashboard(DASHBOARD_NAME);
```
Here is a more complex example that sets up a Grafana dashboard with a panel and row: 
```
import com.appnexus.wsp.grafana.client.GrafanaClient;

//Setup the client
GrafanaConfiguration grafanaConfiguration =
        new GrafanaConfiguration().host("your_grafana_host").apiKey("Bearer your_secret_key");
GrafanaClient grafanaClient = new GrafanaClient(grafanaConfiguration);

//Setup the dashboard
String DASHBOARD_NAME = "new_dashboard";

DashboardPanelTarget dashboardPanelTarget =
    new DashboardPanelTarget().refId("getSomeMetric").target("*");

DashboardPanelXAxis dashboardPanelXAxis =
    new DashboardPanelXAxis().show(true).mode(DashboardPanelXAxis.Mode.TIME);

DashboardPanelYAxis dashboardPanelYAxis =
    new DashboardPanelYAxis().format(DashboardPanelYAxis.Format.SHORT).logBase(1).show(true);

//Datasource is required or alerts cannot be added
DashboardPanel dashboardPanel =
    new DashboardPanel()
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
```

## Javadocs

JavaDocs can be found in the docs directory. You can browse them 
[here](https://appnexus.github.io/grafana-api-java-client/).


## Contributing

If you would like to contribute, please see our current list of issues and/or feature requests
and send us a pull request. If you have something specific that you'd like to add or fix, please
open up an issue for discussion. If it is a fix for a bug or everyone agrees that it would be
a useful feature, then submit your pull request. Make sure that your pull request's commit message
uses one of the [appropriate identifiers](https://github.com/blog/1386-closing-issues-via-commit-messages) 
to link the pull request to the issue.


### Style Guide

This project follows the [Google Java Style](https://google.github.io/styleguide/javaguide.html). 
It is enforced through the [spotless](https://github.com/diffplug/spotless) gradle plugin which runs 
as part of each build. To fix any style issues automatically you can run `./gradlew spotlessApply`


## License

See LICENSE file

## Notice

See NOTICE file
