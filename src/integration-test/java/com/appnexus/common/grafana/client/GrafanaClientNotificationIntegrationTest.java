/* Licensed under Apache-2.0 */
package com.appnexus.common.grafana.client;

import com.appnexus.common.grafana.client.models.AlertNotification;
import com.appnexus.common.grafana.client.models.AlertNotificationSettings;
import com.appnexus.common.grafana.configuration.GrafanaConfiguration;
import com.appnexus.common.grafana.exceptions.GrafanaException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/** To run this integration test a valid API key and host must be added */
public class GrafanaClientNotificationIntegrationTest {

  private static final String GRAFANA_API_KEY = ""; //Replace with valid API key
  private static final String GRAFANA_HOST = ""; // Replace with valid host

  private static final String ALERT_NAME = "test alertNotification";

  private GrafanaConfiguration grafanaConfiguration =
      new GrafanaConfiguration().host(GRAFANA_HOST).apiKey(GRAFANA_API_KEY);

  @Before
  public void before() throws Exception {
    deleteNotificationByName(ALERT_NAME);
  }

  @After
  public void after() throws Exception {
    deleteNotificationByName(ALERT_NAME);
  }

  private Integer createNotificationTest() throws GrafanaException, IOException {
    String httpMethod = "POST";
    String url = "http://localhost/testing";
    String type = "webook";

    AlertNotificationSettings settings =
        new AlertNotificationSettings().httpMethod(httpMethod).url(url);

    AlertNotification alertNotification =
        new AlertNotification().name(ALERT_NAME).type(type).settings(settings);

    GrafanaClient grafanaClient = new GrafanaClient(grafanaConfiguration);
    AlertNotification response = grafanaClient.createNotification(alertNotification);

    assert response.name().equals(alertNotification.name());
    assert response.type().equals(alertNotification.type());
    assert response.settings().httpMethod().equals(alertNotification.settings().httpMethod());
    assert response.settings().url().equals(alertNotification.settings().url());

    return response.id();
  }

  private Integer updateNotificationTest(Integer notificationId)
      throws GrafanaException, IOException {
    String httpMethod = "POST";
    String url = "http://localhost/different_url";
    String name = "test notification2";
    String type = "webook";

    AlertNotificationSettings settings =
        new AlertNotificationSettings().httpMethod(httpMethod).url(url);

    AlertNotification alertNotification =
        new AlertNotification().id(notificationId).name(name).type(type).settings(settings);

    GrafanaClient grafanaClient = new GrafanaClient(grafanaConfiguration);
    AlertNotification response =
        grafanaClient.updateNotification(notificationId, alertNotification);

    assert response.name().equals(alertNotification.name());
    assert response.type().equals(alertNotification.type());
    assert response.settings().httpMethod().equals(alertNotification.settings().httpMethod());
    assert response.settings().url().equals(alertNotification.settings().url());

    return response.id();
  }

  private void getNotificationTest(Integer notificationId) throws GrafanaException, IOException {
    GrafanaClient grafanaClient = new GrafanaClient(grafanaConfiguration);
    AlertNotification response = grafanaClient.getNotification(notificationId);

    assert response.id().equals(notificationId);
  }

  private void deleteNotificationById(Integer notificationId) throws GrafanaException, IOException {
    GrafanaClient grafanaClient = new GrafanaClient(grafanaConfiguration);
    String response = grafanaClient.deleteNotification(notificationId);

    assert response.contains("Notification deleted");
  }

  private void deleteNotificationByName(String name) throws GrafanaException, IOException {
    GrafanaClient grafanaClient = new GrafanaClient(grafanaConfiguration);
    List<AlertNotification> notifications = grafanaClient.getNotifications();

    //Filter notifications and find ALERT_NAME, then delete it
    List<AlertNotification> matchingNotifications =
        notifications.stream().filter(n -> n.name().equals(name)).collect(Collectors.toList());

    if (matchingNotifications.size() == 1) {
      Integer notificationId = matchingNotifications.get(0).id();
      deleteNotificationById(notificationId);
    }
  }

  @Test
  public void notificationTest() throws GrafanaException, IOException {
    Integer notificationId = createNotificationTest();
    updateNotificationTest(notificationId);
    getNotificationTest(notificationId);
    deleteNotificationById(notificationId);
  }
}
