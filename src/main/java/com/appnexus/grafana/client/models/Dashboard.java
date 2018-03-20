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
public class Dashboard extends FlexibleSchemaComponent {
  private Long id;
  private String title;
  private String description;
  private List<DashboardRow> rows;
  private Integer version;
  private Boolean editable;
  private Boolean hideControls;
  private String timezone;
  private List<String> tags;
  private Integer schemaVersion;
  private DashboardAnnotationList annotations;
  private DashboardTemplate templating;
}
