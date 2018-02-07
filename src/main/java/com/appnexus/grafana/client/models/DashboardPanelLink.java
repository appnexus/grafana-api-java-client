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
public class DashboardPanelLink extends FlexibleSchemaComponent {
  // common
  private Type type;
  private Boolean keepTime;
  private String params;
  private Boolean targetBlank;
  private String title;
  // DASHBOARD
  private String dashboard;
  // ABSOLUTE
  private String url;

  public enum Type {
    DASHBOARD("dashboard"),
    ABSOLUTE("absolute");
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
