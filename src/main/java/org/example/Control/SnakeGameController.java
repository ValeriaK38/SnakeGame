package org.example.Control;

import org.example.Model.Apple;
import org.example.Model.Score;
import org.example.Model.Snake;
import org.example.View.GamePanel;

import javax.swing.*;

public class SnakeGameController implements Runnable {

    private static final int DELAY = 75; // The higher the number the slower the game
    private Score score;

    private Apple apple;
    private Snake snake; // singleton snake

    private boolean running = false; // game on status
    private Timer timer;

    private GamePanel renderer;

    public SnakeGameController(GamePanel renderer) {
        this.renderer = renderer;
    }

    @Override
    public void run() {
        snake = new Snake(); // create snake singleton
        score = new Score();
        apple = new Apple(); // create new apple
        running = true;
        timer = new Timer(DELAY, e -> {
            snake.move();
            checkAppleEaten();
            checkCollisions();
            renderer.repaint();
        });
        timer.start();
    }

    public void checkAppleEaten() {
        if (snake.getXAt(0) == apple.getAppleX() && snake.getYAt(0) == apple.getAppleY()) {
            snake.addBodyPart();
            score.addScorePoint();
            apple.relocateApple();
        }
    }
    public void checkCollisions() {
        running = snake.checkSnakeCollisions();
        if (!running) {
            timer.stop();
        }
    }

    public boolean isRunning() {
        return running;
    }

    public Snake getSnake() {
        return snake;
    }

    public Apple getApple() {
        return apple;
    }

    public Score getScore() {
        return score;
    }
}



