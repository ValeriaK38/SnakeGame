package org.example.Model;

public enum Measures {
    SCREEN_WIDTH(600),
    SCREEN_HEIGHT(600),
    UNIT_SIZE(25),
    GAME_UNITS((SCREEN_WIDTH.getValue() * SCREEN_HEIGHT.getValue()) / UNIT_SIZE.getValue());
    private final int value;

    Measures(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
