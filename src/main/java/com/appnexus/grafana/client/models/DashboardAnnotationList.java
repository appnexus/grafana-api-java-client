/* Licensed under Apache-2.0 */
package com.appnexus.grafana.client.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(fluent = true)
@JsonDeserialize(using = DashboardAnnotationListDeserializer.class)
public class DashboardAnnotationList extends FlexibleSchemaComponent {
  final static String LIST_FIELD_NAME = "list";

  private List<DashboardAnnotation> list;
}
