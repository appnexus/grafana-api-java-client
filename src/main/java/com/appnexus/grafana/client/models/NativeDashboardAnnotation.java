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
public class NativeDashboardAnnotation extends DashboardAnnotation {
  final static String DATASOURCE_NAME = "-- Grafana --";

  private Type type;

  public NativeDashboardAnnotation() {
    datasource(DATASOURCE_NAME);
  }

  public enum Type {
    DASHBOARD("dashboard"),
    TAGS("tags");
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
