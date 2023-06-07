package com.ktd.vivacon.framework;

import com.ktd.vivacon.framework.annotation.VivaKeyValue;

import java.util.HashMap;
import java.util.Map;

public final class VivaKeyValueAnnotations {
    private VivaKeyValueAnnotations() {}

    public static String getProperty(String key, VivaKeyValue[] kvs) {
        for (VivaKeyValue kv : kvs) {
            if (key.equals(kv.key())) {
                return kv.value();
            }
        }
        return null;
    }

    public static Map<String, String> getProperties(VivaKeyValue[] kvs) {
        Map<String, String> answer = new HashMap<>();
        for (VivaKeyValue kv : kvs) {
            answer.put(kv.key(), kv.value());
        }
        return answer;
    }
}
