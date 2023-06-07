package com.ktd.vivacon.framework;

public interface VivaKnownTypeElement {
    @SuppressWarnings("rawtypes")
    Class getType();

    default String getTypeName() {
        return getType().getTypeName();
    }
}
