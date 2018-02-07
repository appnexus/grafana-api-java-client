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
public class AlertNotification extends FlexibleSchemaComponent {
  private Integer id;
  private String name;
  private String type;
  private Boolean isDefault;
  private AlertNotificationSettings settings;
  private String created;
  private String updated;
}
