package io.github.moudjames23.scan2dojo.dto.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ProductResponse(
        int id,
        String name,
        String description,
        @JsonProperty("prod_type")
        int prodType,
        @JsonProperty("sla_configuration")
        int slaConfiguration
) implements ResponseMessage, Serializable {

    @Override
    public String success() {
        return "Product created  \uD83C\uDF89\uD83C\uDF89\uD83C\uDF89 \nProductId: " + id;
    }
}
