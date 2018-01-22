/* Licensed under Apache-2.0 */
package com.appnexus.grafana.client.models;

import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class GrafanaSearch {
  Long id;
  String title;
  String uri;
  String type;
  List<String> tags;
  Boolean isStarred;
}
