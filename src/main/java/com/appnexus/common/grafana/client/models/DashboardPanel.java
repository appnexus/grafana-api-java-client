/* Licensed under Apache-2.0 */
package com.appnexus.common.grafana.client.models;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class DashboardPanel {

  DashboardPanelAlert alert;
  String datasource; //required for alerts
  Boolean editable;
  Boolean error;
  Integer fill;
  Integer id;
  Integer span;
  String height;
  Boolean lines;
  Integer linewidth;
  String nullPointMode;
  Boolean percentage;
  List<DashboardPanelTarget> targets;
  String title;
  DashboardPanelXAxis xaxis;
  List<DashboardPanelYAxis> yaxes;
  Type type;
  List<DashboardPanelThreshold> thresholds;

  public enum Type {
    GRAPH("graph");
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
