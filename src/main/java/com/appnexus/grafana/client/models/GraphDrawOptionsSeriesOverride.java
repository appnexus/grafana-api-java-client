/* Licensed under Apache-2.0 */
package com.appnexus.grafana.client.models;

import com.appnexus.grafana.client.models.GraphDashboardPanel.NullPointMode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(fluent = true)
public class GraphDrawOptionsSeriesOverride extends FlexibleSchemaComponent {
  private String alias;
  private Integer fill;
  private Boolean lines;
  private Integer linewidth;
  private Boolean points;
  private Integer pointRadius;
  private NullPointMode nullPointMode;
  private Boolean steppedLine;
  private Boolean stack;
  private Boolean percentage;
  private Boolean dashes;
}

