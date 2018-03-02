/* Licensed under Apache-2.0 */
package com.appnexus.grafana.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import com.appnexus.grafana.client.models.Datasource;
import com.appnexus.grafana.client.models.DatasourceCreationResult;
import com.appnexus.grafana.client.models.DatasourceIdResult;
import com.appnexus.grafana.client.models.GrafanaMessage;
import com.appnexus.grafana.configuration.GrafanaConfiguration;
import com.appnexus.grafana.exceptions.GrafanaException;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/** Created by Alexandre Rio on 01/02/18. */
public class DataSourceTest {

  private static final String GRAFANA_API_KEY = ""; //Replace with valid API key
  private static final String GRAFANA_HOST = ""; // Replace with valid host
  private static final String dsName = "DATASOURCE_TEST";

  private GrafanaConfiguration grafanaConfiguration;
  private GrafanaClient grafanaClient;
  private Datasource ds;

  @Before
  public void setup() {
    grafanaConfiguration =
        new GrafanaConfiguration().host(GRAFANA_HOST).apiKey("Bearer " + GRAFANA_API_KEY);
    grafanaClient = new GrafanaClient(grafanaConfiguration);

    ds =
        new Datasource()
            .name(dsName)
            .isDefault(false)
            .type("influxdb")
            .url("http://influxdb:8086")
            .access("proxy")
            .database("testdb")
            .user("test")
            .password("test");
  }

  @After
  public void clean() {
    try {
      int id = grafanaClient.getDatasourceIdbyName(ds.name()).id();
      grafanaClient.deleteDatasource(id);
    } catch (GrafanaException e) {

    } catch (IOException e) {

    }
  }

  @Test
  public void creationTest() {
    try {
      DatasourceCreationResult dcr = grafanaClient.createDatasource(ds);
      assertEquals(dcr.name(), ds.name());
      assertEquals("Datasource added", dcr.message());
    } catch (IOException e) {
      fail();
    } catch (GrafanaException e) {
      fail();
    }
  }

  @Test
  public void getByNameTest() {
    try {
      DatasourceCreationResult dcr = grafanaClient.createDatasource(ds);
      Datasource d1 = grafanaClient.getDatasource(dsName);
      assertEquals(dcr.id(), d1.id());
    } catch (IOException e) {
      fail();
    } catch (GrafanaException e) {
      fail();
    }
  }

  @Test
  public void getByIdTest() {
    try {
      DatasourceCreationResult dcr = grafanaClient.createDatasource(ds);
      Datasource d = grafanaClient.getDatasource(dcr.id());

      assertEquals(dcr.name(), d.name());
      DatasourceIdResult di1 = grafanaClient.getDatasourceIdbyName(dsName);
    } catch (IOException e) {
      fail();
    } catch (GrafanaException e) {
      fail();
    }
  }

  @Test
  public void deleteTest() {
    try {
      DatasourceCreationResult dcr = grafanaClient.createDatasource(ds);
      GrafanaMessage gm = grafanaClient.deleteDatasource(dcr.id());

      assertEquals("Data source deleted", gm.message());
    } catch (IOException e) {
      fail();
    } catch (GrafanaException e) {
      fail();
    }
  }

  @Test
  public void updateTest() {
    String newName = "newName";
    try {
      DatasourceCreationResult dcr = grafanaClient.createDatasource(ds);
      ds = ds.name(newName);
      dcr = grafanaClient.updateDatasource(ds, dcr.id());
      Datasource result = grafanaClient.getDatasource(dcr.id());

      assertEquals(newName, result.name());
      assertEquals("Datasource updated", dcr.message());
    } catch (IOException e) {
      fail();
    } catch (GrafanaException e) {
      fail();
    }
  }
}
