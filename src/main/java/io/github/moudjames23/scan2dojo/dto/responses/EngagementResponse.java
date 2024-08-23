package io.github.moudjames23.scan2dojo.dto.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EngagementResponse(
        int id,
        String name,
        String description,
        @JsonProperty("product")
        int productId,
        @JsonProperty("target_start")
        String start,
        @JsonProperty("target_end")
        String end
) implements ResponseMessage, Serializable {

    @Override
    public String success() {
        return "Engagement created  \uD83C\uDF89\uD83C\uDF89\uD83C\uDF89 \nEngagementId: " + id;
    }
}
