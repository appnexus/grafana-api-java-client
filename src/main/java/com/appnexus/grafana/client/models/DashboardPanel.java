/* Licensed under Apache-2.0 */
package com.appnexus.grafana.client.models;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(fluent = true)
public class DashboardPanel extends FlexibleSchemaComponent {
  // common
  private Boolean editable;
  private Integer id;
  private Integer span;
  private String height;
  private String title;
  private String description;
  private List<DashboardPanelLink> links;
  private Type type;
  // GRAPH
  private DashboardPanelAlert alert;
  private String datasource; //required for alerts
  private Boolean error;
  private Integer fill;
  private Boolean lines;
  private Integer linewidth;
  private Boolean points;
  private Integer pointRadius;
  private NullPointMode nullPointMode;
  private Boolean steppedLine;
  private Boolean stack;
  private Boolean percentage;
  private Boolean dashes;
  private List<DashboardPanelTarget> targets;
  private DashboardPanelXAxis xaxis;
  private List<DashboardPanelYAxis> yaxes;
  private DashboardPanelLegend legend;
  private List<GraphDrawOptionsSeriesOverride> seriesOverrides;
  private List<DashboardPanelThreshold> thresholds;
  // ALERT_LIST
  private Integer limit;
  private Boolean onlyAlertsOnDashboard;
  private Show show;
  private Integer sortOrder;
  private Set<State> stateFilter;

  public DashboardPanel builtInDatasource() {
    datasource = Datasource.BUILTIN_DATASOURCE_NAME;
    return this;
  }

  public enum NullPointMode {
    CONNECTED("connected"),
    NULL("null"),
    NULL_AS_ZERO("null as zero");
    private final String value;

    NullPointMode(String s) {
      value = s;
    }

    @JsonValue
    public String value() {
      return value;
    }
  }

  public enum Show {
    CURRENT("current"),
    CHANGES("changes");
    private final String value;

    Show(String s) {
      value = s;
    }

    @JsonValue
    public String value() {
      return value;
    }
  }

  public enum State {
    OK("ok"),
    NO_DATA("no_data"),
    PAUSED("paused"),
    EXECUTION_ERROR("execution_error"),
    ALERTING("alerting");
    private final String value;

    State(String s) {
      value = s;
    }

    @JsonValue
    public String value() {
      return value;
    }
  }

  public enum Type {
    GRAPH("graph"),
    ALERT_LIST("alertlist");
    private final String value;

    Type(String s) {
      value = s;
    }

    @JsonValue
    public String value() {
      return value;
    }
  }
}
