/* Licensed under Apache-2.0 */
package com.appnexus.grafana.client.models;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class DashboardPanelAlert {

  List<DashboardPanelAlertCondition> conditions;
  AlertState executionErrorState;
  String frequency;
  Integer handler;
  String message;
  String name;
  AlertState noDataState;
  List<AlertNotification> notifications;

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
