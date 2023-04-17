package org.example.View;

import org.example.Control.SnakeGameController;
import org.example.Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener, Runnable {

//    private SnakeGameController controller;
    private static final int DELAY = 75; // The higher the number the slower the game
    private Score score;

    private Apple apple;
    private Snake snake; // singleton snake

    private boolean running = false; // game on status
    private Timer timer;

    public GamePanel() {
        this.setPreferredSize(new Dimension(Measures.SCREEN_WIDTH.getValue(), Measures.SCREEN_HEIGHT.getValue()));
        this.setBackground(Color.black);
        this.setFocusable(true); // When a component is focusable, it means that the component can be selected by the user for interaction, such as by clicking on it with the mouse or by using the keyboard to navigate to it.
        this.addKeyListener(new MyKeyAdapter());
        run(); // starts the game
    }

    @Override
    public void run() {
        snake = new Snake(); // create snake singleton
        score = new Score();
        apple = new Apple(); // create new apple
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            // paint the grid
            for (int i = 0; i < Measures.SCREEN_HEIGHT.getValue() / Measures.UNIT_SIZE.getValue(); i++) {
                g.drawLine(i * Measures.UNIT_SIZE.getValue(), 0, i * Measures.UNIT_SIZE.getValue(), Measures.SCREEN_HEIGHT.getValue());
                g.drawLine(0, i * Measures.UNIT_SIZE.getValue(), Measures.SCREEN_WIDTH.getValue(), i * Measures.UNIT_SIZE.getValue());
            }
            g.setColor(apple.getColor());
            g.fillOval(apple.getAppleX(), apple.getAppleY(), Measures.UNIT_SIZE.getValue(), Measures.UNIT_SIZE.getValue());

            for (int i = 0; i < snake.getBodyParts(); i++) {
                if (i == 0) { // head of the snake
                    g.setColor(Color.green);
                    g.fillRect(snake.getXAt(i), snake.getYAt(i), Measures.UNIT_SIZE.getValue(), Measures.UNIT_SIZE.getValue());
                } else { // body of the snake
                    g.setColor(new Color(45, 180, 0));
                    g.fillRect(snake.getXAt(i), snake.getYAt(i), Measures.UNIT_SIZE.getValue(), Measures.UNIT_SIZE.getValue());
                }
            }
// draw the score
            g.setColor(Color.red);
            g.setFont(new Font("Ink Free", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + score.getScorePoints(), (Measures.SCREEN_WIDTH.getValue() - metrics.stringWidth("Score: " + score.getScorePoints())) / 2, g.getFont().getSize());
        } else { // game over screen
            gameOver(g);
        }
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

    public void gameOver(Graphics g) {
        // score
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " + score.getScorePoints(), (Measures.SCREEN_WIDTH.getValue() - metrics1.stringWidth("Score: " + score.getScorePoints())) / 2, g.getFont().getSize());

        // game over
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (Measures.SCREEN_WIDTH.getValue() - metrics.stringWidth("Game Over")) / 2, Measures.SCREEN_HEIGHT.getValue() / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            snake.move();
            checkAppleEaten();
            checkCollisions();
        }
        repaint(); // if the game no longer running
    }

    public class MyKeyAdapter extends KeyAdapter {

        @Override
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
}
