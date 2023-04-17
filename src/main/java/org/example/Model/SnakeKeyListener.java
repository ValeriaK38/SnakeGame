package org.example.Model;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeKeyListener extends KeyAdapter {
    private Snake snake;

    public SnakeKeyListener(Snake snake) {
        this.snake = snake;
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                // not allowing it to move in the opposite direction in 180 degrees
                if (snake.getDirection() != Directions.RIGHT) {
                    snake.setDirection(Directions.LEFT);
                }
                break;

            case KeyEvent.VK_RIGHT:
                if (snake.getDirection() != Directions.LEFT) {
                    snake.setDirection(Directions.RIGHT);
                }
                break;

            case KeyEvent.VK_UP:
                if (snake.getDirection() != Directions.DOWN) {
                    snake.setDirection(Directions.UP);
                }
                break;

            case KeyEvent.VK_DOWN:
                if (snake.getDirection() != Directions.UP) {
                    snake.setDirection(Directions.DOWN);
                }
                break;
        }
    }
}

