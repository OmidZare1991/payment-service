package com.payment.logger.util;

public enum ErrorType {
    DATABASE("database"), NETWORK("network"), OTHERS("other");
    private String value;

    ErrorType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
