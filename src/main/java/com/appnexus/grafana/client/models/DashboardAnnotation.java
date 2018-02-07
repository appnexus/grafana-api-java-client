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
abstract public class DashboardAnnotation extends FlexibleSchemaComponent {
  private String name;
  private String datasource;
  private Boolean enable;
  private Boolean hide;
  private String iconColor;
  private Integer builtIn;
  private Integer showIn;
  private Integer limit;
}
