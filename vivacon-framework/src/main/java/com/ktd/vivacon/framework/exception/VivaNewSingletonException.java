package com.ktd.vivacon.framework.exception;

import com.ktd.vivacon.framework.impl.VivaBeanKey;
import lombok.Getter;

import java.io.Serial;

@Getter
public class VivaNewSingletonException extends IllegalStateException {
    @Serial
    private static final long serialVersionUID = -1494071992523176740L;

    private final String errorBeanName;
    private final Class<?> errorClass;
    private final Class<?> singletonClass;

    public VivaNewSingletonException(
            Class<?> singletonClass, Class<?> errorClass, String errorBeanName) {
        super(
                "can't load singleton of class " +
                        singletonClass.getSimpleName() +
                        ", can't set (" + errorBeanName + ", " + errorClass.getSimpleName() + ")"
        );
        this.errorClass = errorClass;
        this.singletonClass = singletonClass;
        this.errorBeanName = errorBeanName;
    }

    public final VivaBeanKey getErrorKey() {
        return VivaBeanKey.of(getErrorBeanName(), getErrorClass());
    }
}
