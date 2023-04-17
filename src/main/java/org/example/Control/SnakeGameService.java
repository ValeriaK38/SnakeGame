package org.example.Control;

import org.example.Model.Measures;

import javax.swing.*;
import java.awt.*;


public class SnakeGameService extends JPanel{
    private SnakeGameController controller;

    public SnakeGameService(SnakeGameController controller) {
        this.controller = controller;
    }

    public void checkAppleEaten() {
        if (controller.getSnake().getXAt(0) == controller.getApple().getAppleX() && controller.getSnake().getYAt(0) == controller.getApple().getAppleY()) {
            controller.getSnake().addBodyPart();
            controller.getScore().addScorePoint();
            controller.getApple().relocateApple();
        }
    }

    public boolean checkCollisions() {
        return controller.getSnake().checkSnakeCollisions();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g) {
        if (controller.isRunning()) {
            // paint the grid
            for (int i = 0; i < Measures.SCREEN_HEIGHT.getValue() / Measures.UNIT_SIZE.getValue(); i++) {
                g.drawLine(i * Measures.UNIT_SIZE.getValue(), 0, i * Measures.UNIT_SIZE.getValue(), Measures.SCREEN_HEIGHT.getValue());
                g.drawLine(0, i * Measures.UNIT_SIZE.getValue(), Measures.SCREEN_WIDTH.getValue(), i * Measures.UNIT_SIZE.getValue());
            }
            g.setColor(controller.getApple().getColor());
            g.fillOval(controller.getApple().getAppleX(), controller.getApple().getAppleY(), Measures.UNIT_SIZE.getValue(), Measures.UNIT_SIZE.getValue());

            for (int i = 0; i < controller.getSnake().getBodyParts(); i++) {
                if (i == 0) { // head of the snake
                    g.setColor(Color.green);
                    g.fillRect(controller.getSnake().getXAt(i), controller.getSnake().getYAt(i), Measures.UNIT_SIZE.getValue(), Measures.UNIT_SIZE.getValue());
                } else { // body of the snake
                    g.setColor(new Color(45, 180, 0));
                    g.fillRect(controller.getSnake().getXAt(i), controller.getSnake().getYAt(i), Measures.UNIT_SIZE.getValue(), Measures.UNIT_SIZE.getValue());
                }
            }
// draw the score
            g.setColor(Color.red);
            g.setFont(new Font("Ink Free", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + controller.getScore().getScorePoints(), (Measures.SCREEN_WIDTH.getValue() - metrics.stringWidth("Score: " + controller.getScore().getScorePoints())) / 2, g.getFont().getSize());
        } else { // game over screen
            gameOver(g);
        }
    }

    public void gameOver(Graphics g) {
        // score
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " + controller.getScore().getScorePoints(), (Measures.SCREEN_WIDTH.getValue() - metrics1.stringWidth("Score: " + controller.getScore().getScorePoints())) / 2, g.getFont().getSize());

        // game over
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (Measures.SCREEN_WIDTH.getValue() - metrics.stringWidth("Game Over")) / 2, Measures.SCREEN_HEIGHT.getValue() / 2);
    }

}
