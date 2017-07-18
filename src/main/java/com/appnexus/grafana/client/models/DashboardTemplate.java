/* Licensed under Apache-2.0 */
package com.appnexus.grafana.client.models;

import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class DashboardTemplate {
  List<DashboardTemplateList> list;
}
