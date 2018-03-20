/* Licensed under Apache-2.0 */
package com.appnexus.grafana.client.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(fluent = true)
public class DashboardPanelThreshold extends FlexibleSchemaComponent {
  private String colorMode;
  private Boolean fill;
  private Boolean line;
  private String op;
  private Long value;
}
