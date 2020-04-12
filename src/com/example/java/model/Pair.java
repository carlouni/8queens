package com.example.java.model;

/**
 * Define a pair of short numbers to be used as coordinates.
 */
public class Pair {
    public short x;
    public short y;

    public Pair(short x, short y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
