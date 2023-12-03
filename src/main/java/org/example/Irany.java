package org.example;

public enum Irany {
    N("N"),
    W("W"),
    S("S"),
    E("E");

    private final String value;

    Irany(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public String value() {
        return this.value;
    }


}
