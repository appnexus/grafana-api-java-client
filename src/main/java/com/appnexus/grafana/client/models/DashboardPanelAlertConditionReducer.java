/* Licensed under Apache-2.0 */
package com.appnexus.grafana.client.models;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(fluent = true)
public class DashboardPanelAlertConditionReducer extends FlexibleSchemaComponent {
  private Type type;
  private List<String> params;

  public enum Type {
    MIN("min"),
    MAX("max"),
    AVG("avg"),
    SUM("sum"),
    COUNT("count"),
    LAST("last"),
    MEDIAN("median");
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
