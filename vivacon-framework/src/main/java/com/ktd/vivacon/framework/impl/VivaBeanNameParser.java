package com.ktd.vivacon.framework.impl;

import com.ktd.vivacon.framework.VivaClasses;
import com.ktd.vivacon.framework.VivaField;
import com.ktd.vivacon.framework.VivaStrings;
import com.ktd.vivacon.framework.annotation.VivaPrototype;
import com.ktd.vivacon.framework.annotation.VivaSingleton;
import com.ktd.vivacon.framework.impl.VivaMethod;

public final class VivaBeanNameParser {
    private VivaBeanNameParser() {}

    public static String getBeanName(Class<?> clazz) {
        if (clazz.isAnnotationPresent(VivaSingleton.class)) {
            return getSingletonName(clazz);
        }
        if (clazz.isAnnotationPresent(VivaPrototype.class)) {
            return getPrototypeName(clazz);
        }
        return VivaClasses.getVariableName(clazz);
    }

    // ============== parse singleton ==================
    public static String getSingletonName(VivaField field) {
        return getSingletonName(field.getAnnotation(VivaSingleton.class), field.getName());
    }

    public static String getSingletonName(VivaMethod method) {
        return getSingletonName(method.getAnnotation(VivaSingleton.class), method.getFieldName());
    }

    public static String getSingletonName(Class<?> clazz) {
        return getSingletonName(clazz, clazz.getAnnotation(VivaSingleton.class));
    }

    public static String getSingletonName(Class<?> clazz, VivaSingleton annotation) {
        return getSingletonName(annotation, VivaClasses.getVariableName(clazz, "Impl"));
    }

    public static String getSingletonName(VivaSingleton annotation, String defaultName) {
        if (annotation == null) {
            return defaultName;
        }
        String value = annotation.value();
        if (VivaStrings.isNoContent(value)) {
            return defaultName;
        }
        return value;
    }

    // ============ parse prototype ==========
    public static String getPrototypeName(VivaField field) {
        return getPrototypeName(field.getAnnotation(VivaPrototype.class), field.getName());
    }

    public static String getPrototypeName(VivaMethod method) {
        return getPrototypeName(method.getAnnotation(VivaPrototype.class), method.getFieldName());
    }

    public static String getPrototypeName(Class<?> clazz) {
        return getPrototypeName(clazz, clazz.getAnnotation(VivaPrototype.class));
    }

    public static String getPrototypeName(Class<?> clazz, VivaPrototype annotation) {
        return getPrototypeName(annotation, VivaClasses.getVariableName(clazz, "Impl"));
    }

    public static String getPrototypeName(VivaPrototype annotation, String defaultName) {
        if (annotation == null) {
            return defaultName;
        }
        String value = annotation.value();
        if (VivaStrings.isNoContent(value)) {
            return defaultName;
        }
        return value;
    }
}
