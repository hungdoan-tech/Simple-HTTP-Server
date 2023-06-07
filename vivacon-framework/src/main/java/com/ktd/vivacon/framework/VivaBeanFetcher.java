package com.ktd.vivacon.framework;

public interface VivaBeanFetcher {
    Object getBean(Class<?> type);

    Object getBean(String name, Class<?> type);

    Object getAnnotatedBean(Class<?> annotationClass);

    @SuppressWarnings("unchecked")
    default <T> T getBeanCast(Class<T> type) {
        return (T) getBean(type);
    }
}
