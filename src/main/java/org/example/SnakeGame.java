package org.example;

import javax.swing.*;

public class SnakeGame implements Runnable{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new SnakeGame());

    }

    @Override
    public void run() {
        new GameFrame();
    }
}