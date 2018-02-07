/* Licensed under Apache-2.0 */
package com.appnexus.grafana.client.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(fluent = true)
public class DashboardRow extends FlexibleSchemaComponent {
  private Boolean showTitle;
  private Boolean collapse;
  private String title;
  private String titleSize;
  private Integer height;
  private List<DashboardPanel> panels;
}
