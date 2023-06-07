package com.ktd.vivacon.framework;

import com.ktd.vivacon.framework.impl.VivaBeanKey;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

@SuppressWarnings("rawtypes")
public interface VivaSingletonFactory {
    Object getSingleton(Class type);

    Object getSingleton(String name, Class type);

    Object getSingleton(Map properties);

    Object getAnnotatedSingleton(Class annotationClass);

    List getSingletons();

    List getSingletons(Map properties);

    List getSingletons(Class... annotationClass);

    List getSingletons(Predicate filter);

    List getSingletonsOf(Class parentClass);

    Map<VivaBeanKey, Object> getSingletonMapByKey();

    Map getProperties(Object singleton);

    Object addSingleton(Object singleton);

    Object addSingleton(String name, Object singleton);

    Object addSingleton(String name, Object singleton, Map properties);

    void addSingletons(Map<String, Object> singletons);

    void addSingletonsByBeanKey(Map<VivaBeanKey, Object> singletons);

    Set<Class> getSingletonClasses();

    @SuppressWarnings("unchecked")
    default <T> T getSingletonCast(Class<T> type) {
        return (T) getSingleton(type);
    }
}
