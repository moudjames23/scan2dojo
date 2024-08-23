package io.github.moudjames23.scan2dojo.dto.requests;

public record ImportRequest(
        Boolean active,
        Boolean verified,

        String scanType,

        String file,

        String productName,

        String engagementName,

        String minimumSeverity
) { }
