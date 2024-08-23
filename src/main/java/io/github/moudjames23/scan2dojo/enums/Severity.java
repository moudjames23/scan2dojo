package io.github.moudjames23.scan2dojo.enums;

public enum Severity implements EnumWithValue{

    INFO("Info"),
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High"),
    CRITICAL("Critical");

    private final String value;

    Severity(String value) {
        this.value = value;
    }


    @Override
    public String getValue() {
        return this.value;
    }
}
