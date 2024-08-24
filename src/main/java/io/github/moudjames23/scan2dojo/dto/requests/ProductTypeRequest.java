package io.github.moudjames23.scan2dojo.dto.requests;

public record ProductTypeRequest (
        String  name,

        String description,

        boolean criticalProduct,

        boolean keyProduct
){}
