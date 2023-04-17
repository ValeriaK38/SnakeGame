package org.example.View;
import org.example.Control.SnakeGameController;
import org.example.Control.SnakeGameService;

import javax.swing.*;

public class GameFrame extends JFrame {

    public GameFrame() {

        SnakeGameController gameController = new SnakeGameController(new SnakeGameService(null));
        SnakeGameService myService = new SnakeGameService(gameController);
        gameController.setSnakeGameService(myService);
        this.add(gameController);
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack(); //This method calculates the preferred size of the frame based on its contents, and then sets the size of the frame to this preferred size
        this.setVisible(true);
        this.setLocationRelativeTo(null); // Displays the frame in the middle of the screen

        Thread gameThread = new Thread(gameController); // the game thread
        gameThread.start();
    }
}
