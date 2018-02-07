/* Licensed under Apache-2.0 */
package com.appnexus.grafana.client.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(fluent = true)
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type")
@JsonSubTypes({
    @Type(value = AbsoluteDashboardPanelLink.class, name = "absolute"),
    @Type(value = RelativeDashboardPanelLink.class, name = "dashboard")
})
abstract public class DashboardPanelLink extends FlexibleSchemaComponent {
  private Type type;
  private Boolean keepTime;
  private String params;
  private Boolean targetBlank;
  private String title;

  public enum Type {
    DASHBOARD("dashboard"),
    ABSOLUTE("absolute");
    private final String value;

    Type(String s) {
        value = s;
    }

    @JsonValue
    public String value() {
      return value;
    }
  }
}
