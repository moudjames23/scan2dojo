package io.github.moudjames23.scan2dojo.dto.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ImportResponse(
        @JsonProperty("scan_date")
        String scanDate,
        @JsonProperty("minimum_severity")
        String minimumSeverity,
        boolean active,
        boolean verified,
        @JsonProperty("scan_type")
        String scanType,
        String file
) implements ResponseMessage, Serializable {

    @Override
    public String success() {
        return "Scan imported  \uD83C\uDF89\uD83C\uDF89\uD83C\uDF89";
    }
}
