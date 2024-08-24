package io.github.moudjames23.scan2dojo.dto.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ProductTypeResponse (

        int id,
        String  name,

        String description,

        @JsonProperty("critical_product")
        boolean criticalProduct,

        @JsonProperty("key_product")
        boolean keyProduct
)implements ResponseMessage, Serializable {
        @Override
        public String success() {
                return "Product type created  \uD83C\uDF89\uD83C\uDF89\uD83C\uDF89 \nId: " + this.id+ "\nName: " +this.name;
        }
}