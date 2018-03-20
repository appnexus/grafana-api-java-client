/* Licensed under Apache-2.0 */
package com.appnexus.grafana.client.models;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Accessors(fluent = true)
public class DashboardPanelAlertConditionEvaluator extends FlexibleSchemaComponent {
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
