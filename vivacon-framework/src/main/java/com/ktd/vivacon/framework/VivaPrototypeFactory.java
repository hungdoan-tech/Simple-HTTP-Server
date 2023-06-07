package com.ktd.vivacon.framework;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@SuppressWarnings("rawtypes")
public interface VivaPrototypeFactory {

    VivaPrototypeSupplier getSupplier(Class objectType);

    VivaPrototypeSupplier getSupplier(String objectName, Class objectType);

    VivaPrototypeSupplier getSupplier(Map properties);

    VivaPrototypeSupplier getAnnotatedSupplier(Class annotationClass);

    List<VivaPrototypeSupplier> getSuppliers();

    List<VivaPrototypeSupplier> getSuppliers(Map properties);

    List<VivaPrototypeSupplier> getSuppliers(Class... annotationClasses);

    List<VivaPrototypeSupplier> getSuppliers(Predicate<VivaPrototypeSupplier> filter);

    List<VivaPrototypeSupplier> getSuppliersOf(Class parentClass);

    Map getProperties(VivaPrototypeSupplier supplier);

    void addSupplier(VivaPrototypeSupplier supplier);

    void addSupplier(String objectName, VivaPrototypeSupplier supplier);

    void addSupplier(String objectName, VivaPrototypeSupplier supplier, Map properties);
}
