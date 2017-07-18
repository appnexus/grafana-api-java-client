/* Licensed under Apache-2.0 */
package com.appnexus.grafana.client.models;

import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class Dashboard {
  Long id;
  String title;
  List<DashboardRow> rows;
  Integer version;
  String timezone;
  List<String> tags;
  Integer schemaVersion;
  DashboardTemplate templating;
}
