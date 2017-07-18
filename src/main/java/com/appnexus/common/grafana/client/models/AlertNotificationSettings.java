/* Licensed under Apache-2.0 */
package com.appnexus.common.grafana.client.models;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class AlertNotificationSettings {
  private String httpMethod;
  private String url;
}
