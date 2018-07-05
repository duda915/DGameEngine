package com.mdud.dengine.states;

import com.mdud.dengine.utility.input.KeyHandler;
import com.mdud.dengine.utility.input.MouseHandler;

import java.awt.*;

public abstract class GameState {
    public abstract void update();
    public abstract void input(MouseHandler mouseHandler, KeyHandler keyHandler);
    public abstract void render(Graphics2D graphics);

}
