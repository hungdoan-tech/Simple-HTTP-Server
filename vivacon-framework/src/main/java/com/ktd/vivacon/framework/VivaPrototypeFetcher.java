package com.ktd.vivacon.framework;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
public interface VivaPrototypeFetcher {

    <T> T getPrototype(Class<T> type);

    <T> T getPrototype(String name, Class<T> type);

    <T> T getPrototype(Map properties);

    <T> T getAnnotatedPrototype(Class annotationClass);

    List getPrototypes(Map properties);

    VivaPrototypeSupplier getPrototypeSupplier(Map properties);

    List<VivaPrototypeSupplier> getPrototypeSuppliers(Map properties);

    List<VivaPrototypeSupplier> getPrototypeSuppliers(Class annotationClass);
}
