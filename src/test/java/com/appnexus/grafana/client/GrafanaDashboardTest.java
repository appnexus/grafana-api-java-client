/* Licensed under Apache-2.0 */
package com.appnexus.grafana.client;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.appnexus.grafana.client.models.Dashboard;
import com.appnexus.grafana.client.models.DashboardMeta;
import com.appnexus.grafana.client.models.GrafanaDashboard;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

public class GrafanaDashboardTest {

  private final ObjectMapper objectMapper =
      new ObjectMapper()
          .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
          .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);;

  @Test
  public void serializationTest() {

    String dashboardType = "db";
    String slug = "test dashbaord";
    String expires = "0001-01-01T00:00:00Z";
    String created = "0001-01-01T00:00:00Z";
    String updated = "0001-01-01T00:00:00Z";
    String updatedBy = "admin";
    String createdBy = "admin";
    Integer version = 0;

    DashboardMeta dashboardMeta =
        new DashboardMeta()
            .type(dashboardType)
            .slug(slug)
            .expires(expires)
            .created(created)
            .updated(updated)
            .updatedBy(updatedBy)
            .createdBy(createdBy)
            .version(version);

    Long id = 1L;
    String title = "title";
    Integer schemaVersion = 1;

    Dashboard dashboard = new Dashboard().id(id).title(title).schemaVersion(schemaVersion);

    GrafanaDashboard grafanaDashboard =
        new GrafanaDashboard().dashboard(dashboard).meta(dashboardMeta);

    String jsonString = null;
    try {
      jsonString = objectMapper.writeValueAsString(grafanaDashboard);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      fail("Could not deserialize grafana dashboard");
    }

    assertTrue(jsonString.contains(dashboardType));
    assertTrue(jsonString.contains(slug));
    assertTrue(jsonString.contains(expires));
    assertTrue(jsonString.contains(created));
    assertTrue(jsonString.contains(updated));
    assertTrue(jsonString.contains(createdBy));
    assertTrue(jsonString.contains(updatedBy));
    assertTrue(jsonString.contains(version.toString()));
    assertTrue(jsonString.contains(id.toString()));
    assertTrue(jsonString.contains(title));
  }
}
