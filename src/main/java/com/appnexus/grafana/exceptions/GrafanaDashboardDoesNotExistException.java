/* Licensed under Apache-2.0 */
package com.appnexus.grafana.exceptions;

import java.io.IOException;
import okhttp3.ResponseBody;

public class GrafanaDashboardDoesNotExistException extends GrafanaException {

  public static GrafanaDashboardDoesNotExistException withErrorBody(ResponseBody body)
      throws IOException {
    return body != null
        ? new GrafanaDashboardDoesNotExistException("Unexpected Grafana error; " + body.string())
        : new GrafanaDashboardDoesNotExistException("Unexpected Grafana error");
  }

  public GrafanaDashboardDoesNotExistException(String message) {
    super(message);
  }

  public GrafanaDashboardDoesNotExistException(String message, Throwable cause) {
    super(message, cause);
  }
}
