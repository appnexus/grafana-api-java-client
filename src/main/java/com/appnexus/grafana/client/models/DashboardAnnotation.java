/* Licensed under Apache-2.0 */
package com.appnexus.grafana.client.models;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(fluent = true)
public class DashboardAnnotation extends FlexibleSchemaComponent {
  // common
  private String name;
  private String datasource;
  private Boolean enable;
  private Boolean hide;
  private String iconColor;
  private Integer builtIn;
  private Integer showIn;
  private Integer limit;
  // BUILTIN
  private Type type;
  // GRAPHITE
  private List<String> tags;
  private String target;

  public DashboardAnnotation builtInDatasource() {
    datasource = Datasource.BUILTIN_DATASOURCE_NAME;
    return this;
  }

  public enum Type {
    DASHBOARD("dashboard"),
    TAGS("tags");
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
