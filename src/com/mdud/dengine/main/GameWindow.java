package com.mdud.dengine.main;

import javax.swing.*;

public class GameWindow extends JFrame {

    public GameWindow() {
        setTitle("DGameEngine");
        setContentPane(new GamePanel(480*2, 48*10));
        pack();
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
