package com.mdud.dengine.main;

import javax.swing.*;

public class GameWindow extends JFrame {

    public GameWindow() {
        setTitle("DGameEngine");
        setContentPane(new GamePanel(640, 360));
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
