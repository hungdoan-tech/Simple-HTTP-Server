package com.ktd.vivacon.framework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unchecked")
public final class VivaLists {
    private VivaLists() {
    }

    public static <T> List<T> newArrayList(T... args) {
        List<T> list = new ArrayList<>();
        Collections.addAll(list, args);
        return list;
    }

    public static <T> List<T> newArrayList(Iterable<T> iterable) {
        List<T> list = new ArrayList<>();
        for (T t : iterable) {
            list.add(t);
        }
        return list;
    }

    public static <T> List<T> tryNewArrayList(Iterable<T> iterable) {
        if (iterable instanceof List) {
            return (List<T>) iterable;
        }
        return newArrayList(iterable);
    }

    public static <T> List<T> toList(Iterable<T> iterable) {
        if (iterable instanceof List) {
            return (List<T>) iterable;
        }
        return newArrayList(iterable);
    }
}
