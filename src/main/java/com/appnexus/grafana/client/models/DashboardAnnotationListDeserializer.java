/* Licensed under Apache-2.0 */
package com.appnexus.grafana.client.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.appnexus.grafana.client.models.DashboardAnnotation.DATASOURCE_FIELD_NAME;
import static com.appnexus.grafana.client.models.DashboardAnnotationList.LIST_FIELD_NAME;

class DashboardAnnotationListDeserializer extends JsonDeserializer<DashboardAnnotationList> {

  @Override
  public DashboardAnnotationList deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    ObjectMapper mapper = (ObjectMapper) p.getCodec();
    ObjectNode root = mapper.readTree(p);
    ArrayNode listNode = (ArrayNode) root.get(LIST_FIELD_NAME);
    List<DashboardAnnotation> annotations = new ArrayList<>();

    for (JsonNode annotationNode : listNode) {
      Class<? extends DashboardAnnotation> subTypeClass =
          NativeDashboardAnnotation.DATASOURCE_NAME.equals(annotationNode.get(DATASOURCE_FIELD_NAME).asText())
              ? NativeDashboardAnnotation.class
              : GraphiteDashboardAnnotation.class;
      String annotationJson = annotationNode.toString();
      DashboardAnnotation annotation = mapper.readValue(annotationJson, subTypeClass);

      annotations.add(annotation);
    }

    return new DashboardAnnotationList().list(annotations);
  }

}
