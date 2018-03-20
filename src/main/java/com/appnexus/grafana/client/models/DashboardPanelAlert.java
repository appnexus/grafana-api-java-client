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
public class DashboardPanelAlert extends FlexibleSchemaComponent {
  private List<DashboardPanelAlertCondition> conditions;
  private AlertState executionErrorState;
  private String frequency;
  private Integer handler;
  private String message;
  private String name;
  private AlertState noDataState;
  private List<AlertNotification> notifications;

  public enum AlertState {
    ALERTING("alerting"),
    NO_DATA("no_data"),
    KEEP_LAST_STATE("keep_state"),
    OK("ok");
    private final String value;

    AlertState(String s) {
      value = s;
    }

    @JsonValue
    public String value() {
      return value;
    }
  }
}
