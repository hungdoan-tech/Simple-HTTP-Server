package com.ktd.vivacon.framework.annotation;

import java.lang.annotation.*;

@Documented
@Target({
        ElementType.FIELD,
        ElementType.METHOD
})
@Retention(RetentionPolicy.RUNTIME)
public @interface VivaProperty {

    String value() default "";

    String prefix() default "";
}
