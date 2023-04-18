package org.example.Control;

import org.example.Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakeGameController extends JPanel implements ActionListener, Runnable {

    private static final int DELAY = 75; // The higher the number the slower the game
    private Score score;

    private Apple apple;
    private Snake snake; // singleton snake

    private boolean running = false; // game on status
    private Timer timer;

    private SnakeGameService gameService;

    public SnakeGameController(SnakeGameService gameService) {
        this.gameService = gameService;
        this.setPreferredSize(new Dimension(Measures.SCREEN_WIDTH.getValue(), Measures.SCREEN_HEIGHT.getValue()));
        this.setBackground(Color.black);
        this.setFocusable(true); // When a component is focusable, it means that the component can be selected by the user for interaction, such as by clicking on it with the mouse or by using the keyboard to navigate to it.
        run(); // starts the game

    }

    public void setSnakeGameService(SnakeGameService myService) {
        this.gameService = myService;
    }

    @Override
    public void run() {
        snake = new Snake(); // create snake singleton
        score = new Score();
        apple = new Apple(); // create new apple
        running = true;
        timer = new Timer(DELAY, this);
        this.addKeyListener(new SnakeKeyListener(snake));
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            snake.move();
            gameService.checkAppleEaten();
            if (gameService.checkCollisions()) {
                timer.stop();
            } else
                running = false;
        }
        repaint(); // if the game no longer running
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        gameService.draw(g);
    }

    public void gameOver(Graphics g) {
        gameService.gameOver(g);
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