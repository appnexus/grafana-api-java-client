/* Licensed under Apache-2.0 */
package com.appnexus.grafana.client.models;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=true)
@Accessors(fluent = true)
public class GraphDashboardPanel extends DashboardPanel {
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
}

