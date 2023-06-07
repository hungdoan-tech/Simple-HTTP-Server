package com.ktd.vivacon.framework.exception;

import com.ktd.vivacon.framework.impl.VivaBeanKey;

import java.io.Serial;
import java.util.Set;

public class VivaSingletonException extends IllegalStateException {
    @Serial
    private static final long serialVersionUID = 814337130118800149L;

    public VivaSingletonException(String msg) {
        super(msg);
    }

    public static VivaSingletonException implementationNotFound(
            VivaBeanKey key,
            Set<Class<?>> uncompleted
    ) {
        return new VivaSingletonException(
                "bean " +
                        key +
                        " implementation not found, uncompleted classes: " +
                        uncompleted
        );
    }
}
