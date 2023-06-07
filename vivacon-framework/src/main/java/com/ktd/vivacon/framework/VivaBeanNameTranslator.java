package com.ktd.vivacon.framework;

public interface VivaBeanNameTranslator {
    String translate(String name, Class<?> type);

    void map(String freeName, Class<?> type, String realName);
}
