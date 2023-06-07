package com.ktd.vivacon.framework.impl;

import com.ktd.vivacon.framework.VivaBeanNameTranslator;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class VivaSimpleBeanNameTranslator implements VivaBeanNameTranslator {
    protected final Map<VivaBeanKey, String> map = new ConcurrentHashMap<>();

    @Override
    public String translate(String name, Class<?> type) {
        VivaBeanKey key = VivaBeanKey.of(name, type);
        return map.getOrDefault(key, name);
    }

    @Override
    public void map(String freeName, Class<?> type, String realName) {
        map.put(VivaBeanKey.of(freeName, type), realName);
    }
}
