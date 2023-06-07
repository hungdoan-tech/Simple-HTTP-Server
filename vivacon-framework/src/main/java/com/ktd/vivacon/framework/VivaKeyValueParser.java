package com.ktd.vivacon.framework;

import com.ktd.vivacon.framework.annotation.VivaKeyValue;
import com.ktd.vivacon.framework.annotation.VivaPrototype;
import com.ktd.vivacon.framework.annotation.VivaSingleton;

import java.util.Map;

@SuppressWarnings({"rawtypes"})
public final class VivaKeyValueParser {
    private VivaKeyValueParser() {}

    public static Map getSingletonProperties(Class<?> clazz) {
        return getSingletonProperties(clazz.getAnnotation(VivaSingleton.class));
    }

    public static Map getSingletonProperties(VivaSingleton annotation) {
        VivaKeyValue[] keyValues = annotation != null ? annotation.properties() : new VivaKeyValue[0];
        return VivaKeyValueAnnotations.getProperties(keyValues);
    }

    public static Map getPrototypeProperties(Class<?> clazz) {
        return getPrototypeProperties(clazz.getAnnotation(VivaPrototype.class));
    }

    public static Map getPrototypeProperties(VivaPrototype annotation) {
        VivaKeyValue[] keyValues = annotation != null ? annotation.properties() : new VivaKeyValue[0];
        return VivaKeyValueAnnotations.getProperties(keyValues);
    }
}
