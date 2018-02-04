package com.appnexus.grafana.client.models;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class DatasourceCreationResult {
    int id;
    String message;
    String name;
}
