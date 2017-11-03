/* Licensed under Apache-2.0 */
package com.appnexus.grafana.exceptions;

import java.io.IOException;
import okhttp3.ResponseBody;

public class GrafanaDashboardCouldNotDeleteException extends GrafanaException {

  public static GrafanaDashboardCouldNotDeleteException withErrorBody(ResponseBody body)
      throws IOException {
    return body != null
        ? new GrafanaDashboardCouldNotDeleteException("Unexpected Grafana error; " + body.string())
        : new GrafanaDashboardCouldNotDeleteException("Unexpected Grafana error");
  }

  public GrafanaDashboardCouldNotDeleteException(String message) {
    super(message);
  }

  public GrafanaDashboardCouldNotDeleteException(String message, Throwable cause) {
    super(message, cause);
  }
}
