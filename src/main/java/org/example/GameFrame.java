package org.example;
import org.example.View.GamePanel;

import javax.swing.*;

public class GameFrame extends JFrame {

    GameFrame() {

//        SnakeGameController controller = new SnakeGameController();
        GamePanel gamePanel = new GamePanel();
        this.add(gamePanel);
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack(); //This method calculates the preferred size of the frame based on its contents, and then sets the size of the frame to this preferred size
        this.setVisible(true);
        this.setLocationRelativeTo(null); // Displays the frame in the middle of the screen

        Thread gameThread = new Thread(gamePanel); // the game thread
        gameThread.start();
    }
}