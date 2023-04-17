package org.example.View;

import org.example.Control.SnakeGameService;
import org.example.Model.Measures;

import javax.swing.*;
import java.awt.*;

public class SnakeGameDraw extends JPanel {

    private SnakeGameService gameService;

    public SnakeGameDraw(SnakeGameService gameService) {
        this.gameService = gameService;
        this.setPreferredSize(new Dimension(Measures.SCREEN_WIDTH.getValue(), Measures.SCREEN_HEIGHT.getValue()));
        this.setBackground(Color.black);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameService.draw(g);
    }

    public void gameOver(Graphics g) {
        gameService.gameOver(g);
    }

}
