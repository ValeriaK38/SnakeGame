package org.example.Model;

import java.util.Arrays;

public class Snake {

    private final int[] x = new int[Measures.GAME_UNITS.getValue()]; // to hold the x coordinates of the body parts of the snake
    private final int[] y = new int[Measures.GAME_UNITS.getValue()]; // to hold the y coordinates of the body parts of the snake
    private int bodyParts = 6; // initial length of the snake by units

    private Directions direction = Directions.RIGHT; // R = right, L = left, U = up, D = down. the initial direction of the snake

    public Directions getDirection() {
        return direction;
    }

    public void setDirection(Directions direction) {
        this.direction = direction;
    }

    public int[] getX() {
        return x;
    }

    public int getXAt(int index) {
        return this.x[index];
    }

    public int[] getY() {
        return y;
    }

    public int getYAt(int index) {
        return this.y[index];
    }

    public int getBodyParts() {
        return bodyParts;
    }

    public void setBodyParts(int bodyParts) {
        this.bodyParts = bodyParts;
    }

    public Snake() {
        this.bodyParts = bodyParts;
    }

    private static Snake snake;

    public static Snake getInstance() {
        if (snake == null) {
            snake = new Snake();
        }
        return snake;
    }

    public void addBodyPart() {
        this.bodyParts++;
    }

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        switch (direction) {
            case RIGHT:
                x[0] = x[0] + Measures.UNIT_SIZE.getValue();
                break;
            case LEFT:
                x[0] = x[0] -  Measures.UNIT_SIZE.getValue();
                break;
            case UP:
                y[0] = y[0] -  Measures.UNIT_SIZE.getValue();
                break;
            case DOWN:
                y[0] = y[0] +  Measures.UNIT_SIZE.getValue();
                break;
        }
    }

    public boolean checkSnakeCollisions() {
        // checks if the head touches the body
        for (int i = this.getBodyParts(); i > 0; i--) {
            //x[0] and y[0] represent the head of the snake
            if (this.getXAt(0) == this.getXAt(i) && this.getYAt(0) == this.getYAt(i)) {
                return false;
            }
        }
        // checks if the head touches the left border
        if (this.getXAt(0) < 0) {
            return false;
        }

        // checks if the head touches the right border
        if (this.getXAt(0) >=  Measures.SCREEN_WIDTH.getValue()) {
            return false;
        }

        // checks if the head touches the top border
        if (this.getYAt(0) < 0) {
            return false;
        }

        // checks if the head touches the bottom border
        if (this.getYAt(0) >=  Measures.SCREEN_HEIGHT.getValue()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Snake{" +
                "x=" + Arrays.toString(x) +
                ", y=" + Arrays.toString(y) +
                ", bodyParts=" + bodyParts +
                ", direction=" + direction +
                '}';
    }
}

