package com.ktd.vivacon.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Import classes to the context to manage or process.
 *
 * @author
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface VivaImport {

    /**
     * the array of classes.
     *
     * @return array of classes
     */
    Class<?>[] value();
}
