package io.github.moudjames23.scan2dojo.dto.requests;


public record EngagementRequest (
        String name,

        String description,

        int productId,

        String start,

        String end
)
{ }
