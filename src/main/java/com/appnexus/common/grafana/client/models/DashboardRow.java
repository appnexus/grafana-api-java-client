/* Licensed under Apache-2.0 */
package com.appnexus.common.grafana.client.models;

import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class DashboardRow {
  Boolean collapse;
  String title;
  String titleSize;
  Integer height;
  List<DashboardPanel> panels;
}
