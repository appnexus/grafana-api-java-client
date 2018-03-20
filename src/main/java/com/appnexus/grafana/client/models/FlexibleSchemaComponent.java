/* Licensed under Apache-2.0 */
package com.appnexus.grafana.client.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@Data
@Accessors(fluent = true)
public class FlexibleSchemaComponent {
  @JsonIgnore
  @Getter(onMethod=@__(@JsonAnyGetter))
  private Map<String, Object> unrecognizedFields = new HashMap<>();

  @JsonAnySetter
  public void setUnrecognizedField(String name, Object value) {
    unrecognizedFields.put(name, value);
  }
}