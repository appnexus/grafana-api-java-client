/* Licensed under Apache-2.0 */
package com.appnexus.grafana.client.models;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class DashboardMeta {
  String type;
  Boolean canSave;
  Boolean canEdit;
  Boolean canStar;
  String slug;
  String expires;
  String created;
  String updated;
  String updatedBy;
  String createdBy;
  Integer version;
  Integer folderId;
  String folderUrl;
  String folderTitle;
}
