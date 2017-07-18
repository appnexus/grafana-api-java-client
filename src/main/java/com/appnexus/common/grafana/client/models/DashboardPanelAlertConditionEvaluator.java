/* Licensed under Apache-2.0 */
package com.appnexus.common.grafana.client.models;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(fluent = true)
public class DashboardPanelAlertConditionEvaluator {

  List<Double> params;
  Type type;

  public enum Type {
    LESS_THAN("lt"),
    GREATER_THAN("gt");
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
