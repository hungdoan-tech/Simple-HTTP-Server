package com.ktd.vivacon.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Excludes the classes in this annotation.
 *
 * @author
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface VivaExclusiveClassesConfiguration {

    Class<?>[] value();
}
