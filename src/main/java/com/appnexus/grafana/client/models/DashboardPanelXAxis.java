/* Licensed under Apache-2.0 */
package com.appnexus.grafana.client.models;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class DashboardPanelXAxis {

  Mode mode;
  String name;
  Boolean show;

  public enum Mode {
    TIME("time"),
    SERIES("series");
    private final String value;

    Mode(String s) {
      value = s;
    }

    @JsonValue
    public String value() {
      return value;
    }
  }
}
