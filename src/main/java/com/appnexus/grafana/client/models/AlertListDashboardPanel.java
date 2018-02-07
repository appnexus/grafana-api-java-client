/* Licensed under Apache-2.0 */
package com.appnexus.grafana.client.models;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(fluent = true)
public class AlertListDashboardPanel extends DashboardPanel {
  private Integer limit;
  private Boolean onlyAlertsOnDashboard;
  private Show show;
  private Integer sortOrder;
  private Set<State> stateFilter;

  public enum Show {
    CURRENT("current"),
    CHANGES("changes");
    private final String value;

    Show(String s) {
      value = s;
    }

    @JsonValue
    public String value() {
      return value;
    }
  }

  public enum State {
    OK("ok"),
    NO_DATA("no_data"),
    PAUSED("paused"),
    EXECUTION_ERROR("execution_error"),
    ALERTING("alerting");
    private final String value;

    State(String s) {
      value = s;
    }

    @JsonValue
    public String value() {
      return value;
    }
  }
}
