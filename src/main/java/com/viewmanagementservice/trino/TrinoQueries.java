package com.viewmanagementservice.trino;

import java.util.List;

public record TrinoQueries(
        String namespace,
        List<String> schemaQueries,
        List<String> viewQueries
) {
}
