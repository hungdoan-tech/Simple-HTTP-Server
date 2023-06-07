package com.ktd.vivacon.framework.supplier;

import com.ktd.vivacon.framework.VivaPrototypeSupplier;

import java.util.ArrayList;

public final class VivaArrayListSupplier implements VivaPrototypeSupplier {

    private static final VivaArrayListSupplier INSTANCE = new VivaArrayListSupplier();

    private VivaArrayListSupplier() {}

    public static VivaArrayListSupplier getInstance() {
        return INSTANCE;
    }

    /*@Override
    public Object supply(VivaBeanContext context) {
        return new ArrayList<>();
    }*/

    @Override
    public Class<?> getObjectType() {
        return ArrayList.class;
    }
}
