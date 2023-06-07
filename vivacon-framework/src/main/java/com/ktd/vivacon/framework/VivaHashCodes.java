package com.ktd.vivacon.framework;

public class VivaHashCodes {
    protected final int initial;
    protected final int prime;
    protected int hashCode;

    public VivaHashCodes() {
        this(1, 31);
    }

    public VivaHashCodes(int initial, int prime) {
        this.initial = initial;
        this.prime = prime;
        this.hashCode = initial;
    }

    public int toHashCode() {
        return hashCode;
    }

    public VivaHashCodes append(Object value) {
        this.hashCode = hashCode * prime + (value == null ? 43 : value.hashCode());
        return this;
    }
}
