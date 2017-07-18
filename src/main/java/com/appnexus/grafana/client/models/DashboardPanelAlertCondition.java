/* Licensed under Apache-2.0 */
package com.appnexus.grafana.client.models;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(fluent = true)
public class DashboardPanelAlertCondition {

  DashboardPanelAlertConditionEvaluator evaluator;
  DashboardPanelAlertConditionQuery query;
  DashboardPanelAlertConditionReducer reducer;
  DashboardPanelAlertConditionOperator operator;
  Type type;

  public enum Type {
    QUERY("query");
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
