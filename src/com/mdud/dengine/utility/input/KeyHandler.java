package com.mdud.dengine.utility.input;

import com.mdud.dengine.main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class KeyHandler implements KeyListener {

    private static List<Key> keyList = new ArrayList<>();

    public KeyHandler(GamePanel gamePanel) {
        gamePanel.addKeyListener(this);
    }

    public class Key {
        private int presses, absorbs;
        private boolean down, clicked;

        public Key() {
            keyList.add(this);
        }

        private void toggle(boolean pressed) {
            if(pressed != down) {
                down = pressed;
            }
            if(pressed) {
                presses++;
            }
        }

        private void tick() {
            if(absorbs < presses) {
                absorbs++;
                clicked = true;
            } else {
                clicked = false;
            }
        }

        public boolean isDown() {
            return down;
        }
    }

    public Key keyUp = new Key();
    public Key keyDown = new Key();
    public Key keyLeft = new Key();
    public Key keyRight = new Key();

    public void releaseAll() {
        for(Key k : keyList) {
            k.down = false;
        }
    }

    public void tick() {
        for(Key k : keyList) {
            k.tick();
        }
    }

    private void toggle(KeyEvent e, boolean pressed) {
        if(e.getKeyCode() == KeyEvent.VK_W) keyUp.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_S) keyDown.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_A) keyLeft.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_D) keyRight.toggle(pressed);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        toggle(e, true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        toggle(e, false);
    }
}
