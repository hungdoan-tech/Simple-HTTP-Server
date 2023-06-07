package com.ktd.vivacon.framework.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.METHOD
})
public @interface VivaPostInit {

    String[] value() default "";
}
