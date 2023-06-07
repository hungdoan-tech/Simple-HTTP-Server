package com.ktd.vivacon.framework.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.FIELD,
        ElementType.METHOD,
        ElementType.CONSTRUCTOR
})
public @interface VivaAutoBind {

    String[] value() default {};
}
