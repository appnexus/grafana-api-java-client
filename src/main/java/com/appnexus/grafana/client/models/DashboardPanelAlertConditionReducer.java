/* Licensed under Apache-2.0 */
package com.appnexus.grafana.client.models;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class DashboardPanelAlertConditionReducer {

  Type type;
  List<String> params;

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
