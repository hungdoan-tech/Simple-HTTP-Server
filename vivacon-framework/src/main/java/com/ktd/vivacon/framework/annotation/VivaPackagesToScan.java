package com.ktd.vivacon.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that a class configure which packages bean context need to load.
 *
 * @author
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface VivaPackagesToScan {

    /**
     * packages to load.
     *
     * @return array of package names
     */
    String[] value() default {};
}
