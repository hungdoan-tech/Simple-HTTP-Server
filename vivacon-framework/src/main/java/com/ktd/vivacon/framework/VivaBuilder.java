package com.ktd.vivacon.framework;

public interface VivaBuilder<T> {
    /**
     * build a product.
     *
     * @return the constructed product
     */
    T build();
}
