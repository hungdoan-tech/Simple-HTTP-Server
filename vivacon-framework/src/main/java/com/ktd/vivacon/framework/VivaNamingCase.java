package com.ktd.vivacon.framework;

import lombok.Getter;

public enum VivaNamingCase {
    NATURE(""),
    UPPER(""),
    LOWER(""),
    CAMEL(""),
    DASH("-"),
    DOT("."),
    UNDERSCORE("_");

    @Getter
    private final String sign;

    VivaNamingCase(String sign) {
        this.sign = sign;
    }

    public static VivaNamingCase of(String value) {
        if (value == null) {
            return NATURE;
        }
        return valueOf(value);
    }
}
