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
public class DashboardMeta extends FlexibleSchemaComponent {
  private String type;
  private Boolean canSave;
  private Boolean canEdit;
  private Boolean canStar;
  private String slug;
  private String expires;
  private String created;
  private String updated;
  private String updatedBy;
  private String createdBy;
  private Integer version;
}
