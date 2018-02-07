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
public class GrafanaSearchResult extends FlexibleSchemaComponent {
  private Long id;
  private String title;
  private String uri;
  private String type;
  private List<String> tags;
  private Boolean isStarred;
}
