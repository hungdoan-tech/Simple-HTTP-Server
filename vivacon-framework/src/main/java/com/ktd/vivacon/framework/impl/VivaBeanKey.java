package com.ktd.vivacon.framework.impl;


import com.ktd.vivacon.framework.VivaEquals;
import com.ktd.vivacon.framework.VivaHashCodes;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
public class VivaBeanKey implements Serializable {
    @Serial
    private static final long serialVersionUID = -2376464316946102262L;

    protected String name;
    protected Class<?> type;

    public VivaBeanKey(String name, Class<?> type) {
        this.name = name;
        this.type = type;
    }

    public static VivaBeanKey of(String name, Class<?> type) {
        return new VivaBeanKey(name, type);
    }

    @Override
    public boolean equals(Object obj) {
        return new VivaEquals<VivaBeanKey>()
                .function(o -> o.name)
                .function(o -> o.type)
                .isEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return new VivaHashCodes()
                .append(name)
                .append(type)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "(" + name + "," + type.getSimpleName() + ")";
    }
}
