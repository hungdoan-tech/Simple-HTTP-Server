package com.ktd.vivacon.framework;

import com.ktd.vivacon.framework.impl.VivaBeanKey;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@SuppressWarnings("rawtypes")
public interface VivaSingletonFetcher {
    <T> T getSingleton(Class<T> type);

    <T> T getSingleton(String name, Class<T> type);

    <T> T getSingleton(Map properties);

    List getSingletons();

    List getSingletons(Map properties);

    List getSingletons(Class annotationClass);

    List getSingletons(Predicate filter);

    List getSingletonsOf(Class parentClass);

    <T> T getAnnotatedSingleton(Class annotationClass);

    Map<VivaBeanKey, Object> getSingletonMapByKey();
}
