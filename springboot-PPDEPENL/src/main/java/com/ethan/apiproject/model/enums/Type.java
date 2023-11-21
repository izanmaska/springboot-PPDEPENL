package com.ethan.apiproject.model.enums;

public enum Type {
    B2C(0), B2B(1);

    private final int code;

    Type(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Type fromCode(int code) {
        for (Type type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid Type code: " + code);
    }
}