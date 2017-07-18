/* Licensed under Apache-2.0 */
package com.appnexus.grafana.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(fluent = true)
public class GrafanaConfiguration {
  private String host;

  private String apiKey;
}
