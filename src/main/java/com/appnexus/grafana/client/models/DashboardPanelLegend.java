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
public class DashboardPanelLegend extends FlexibleSchemaComponent {
  private Boolean show;
  private Boolean alignAsTable;
  private Integer sideWidth;
  private Boolean values;
  private Boolean min;
  private Boolean max;
  private Boolean avg;
  private Boolean current;
  private Boolean total;
  private Boolean rightSide;
  private Boolean hideEmpty;
  private Boolean hideZero;
}
