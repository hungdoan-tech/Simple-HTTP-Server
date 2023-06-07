package com.ktd.vivacon.framework;

import java.lang.annotation.Annotation;

public interface VivaAnnotatedElement {
    <T extends Annotation> T getAnnotation(Class<T> annClass);

    boolean isAnnotated(Class<? extends Annotation> annClass);
}
