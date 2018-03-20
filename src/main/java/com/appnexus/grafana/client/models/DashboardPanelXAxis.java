/* Licensed under Apache-2.0 */
package com.appnexus.grafana.client.models;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(fluent = true)
public class DashboardPanelXAxis extends FlexibleSchemaComponent {
  private Mode mode;
  private String name;
  private Boolean show;

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
