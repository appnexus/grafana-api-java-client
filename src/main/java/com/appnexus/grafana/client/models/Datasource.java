/* Licensed under Apache-2.0 */
package com.appnexus.grafana.client.models;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class Datasource {
  String name;
  boolean isDefault;
  String type;
  String url;
  String access;
  String database;
  String user;
  String password;
}
