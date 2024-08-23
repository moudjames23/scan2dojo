package io.github.moudjames23.scan2dojo.enums;



public enum HttpMethod {

    POST(1), GET(2);

    private final int code;

    HttpMethod(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
