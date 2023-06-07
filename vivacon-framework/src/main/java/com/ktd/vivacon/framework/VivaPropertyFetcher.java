package com.ktd.vivacon.framework;

public interface VivaPropertyFetcher {
    <T> T getProperty(Object key, Class<T> outType);

    default <T> T getProperty(Object key, Class<T> outType, T defaultValue) {
        T value = getProperty(key, outType);
        return value != null ? value : defaultValue;

    }
}
