package com.andersen.hoover.oleg.entity;

public enum Direction {
    R(1, 0),
    L(-1, 0),
    U(0, 1),
    D(0, -1);

    private final int xShift;
    private final int yShift;

    Direction(int xShift, int yShift) {
        this.xShift = xShift;
        this.yShift = yShift;
    }

    public int getXShift() {
        return xShift;
    }

    public int getYShift() {
        return yShift;
    }
}
