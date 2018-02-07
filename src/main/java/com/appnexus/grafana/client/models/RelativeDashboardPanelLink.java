/* Licensed under Apache-2.0 */
package com.appnexus.grafana.client.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper=true)
@Accessors(fluent = true)
public class RelativeDashboardPanelLink extends DashboardPanelLink {
  private String dashboard;
}
