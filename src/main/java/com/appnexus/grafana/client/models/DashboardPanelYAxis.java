/* Licensed under Apache-2.0 */
package com.appnexus.grafana.client.models;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class DashboardPanelYAxis {

  Format format;
  String label;
  Integer logBase;
  Integer max;
  Integer min;
  Boolean show;

  public enum Format {
    NONE("none"),
    SHORT("short");
    private final String value;

    Format(String s) {
      value = s;
    }

    @JsonValue
    public String value() {
      return value;
    }
  }
}
