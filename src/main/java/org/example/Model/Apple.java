package org.example.Model;

import java.awt.*;
import java.util.Random;

public class Apple {
    private int appleX;
    private int appleY;
    private Random random = new Random();

    private Color color;

    public int getAppleX() {
        return appleX;
    }

    public void setAppleX(int appleX) {
        this.appleX = appleX;
    }

    public int getAppleY() {
        return appleY;
    }

    public void setAppleY(int appleY) {
        this.appleY = appleY;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Apple() {

        appleX = random.nextInt(Measures.SCREEN_WIDTH.getValue() / Measures.UNIT_SIZE.getValue()) * Measures.UNIT_SIZE.getValue();
        appleY = random.nextInt(Measures.SCREEN_HEIGHT.getValue() / Measures.UNIT_SIZE.getValue()) * Measures.UNIT_SIZE.getValue();
        color = Color.red;
    }

    public void relocateApple(){
        this.setAppleX(random.nextInt(Measures.SCREEN_WIDTH.getValue() / Measures.UNIT_SIZE.getValue()) * Measures.UNIT_SIZE.getValue()) ;
        this.setAppleY(random.nextInt(Measures.SCREEN_HEIGHT.getValue() / Measures.UNIT_SIZE.getValue()) * Measures.UNIT_SIZE.getValue());
    }

    @Override
    public String toString() {
        return "Apple{" +
                "appleX=" + appleX +
                ", appleY=" + appleY +
                ", color=" + color +
                '}';
    }
}