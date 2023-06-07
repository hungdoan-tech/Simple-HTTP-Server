package com.ktd.vivacon.framework.exception;

import com.ktd.vivacon.framework.VivaField;

import java.io.Serial;
import java.lang.reflect.Field;

public class VivaMissingSetterException extends IllegalStateException {
    @Serial
    private static final long serialVersionUID = -9120694192282292802L;

    public VivaMissingSetterException(VivaField field) {
        this(field.getField());
    }

    public VivaMissingSetterException(Field field) {
        super("missing setter for field: " + field.getName() + " on " + field.getDeclaringClass());
    }
}
