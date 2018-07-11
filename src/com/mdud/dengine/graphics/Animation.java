package com.mdud.dengine.graphics;

import java.awt.image.BufferedImage;

public class Animation {
    private int currentFrame;
    private int delay;
    private int frames;
    private int animationTick;

    public Animation(int delay, int frames) {
        currentFrame = 0;
        animationTick = 0;
        this.delay = delay;
        this.frames = frames;
    }

    public Animation() {
        delay = -1;
        frames = 1;
    }

    public void update() {

        //not animated objects
        if(delay == -1 ) {
            return;
        }

        animationTick++;

        if(animationTick == delay) {
            currentFrame++;
            animationTick = 0;

            if(currentFrame == frames)
                currentFrame = 0;
        }

    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void setFrames(int frames) {
        this.frames = frames;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }


    public int getDelay() {
        return delay;
    }

    public int getFrames() {
        return frames;
    }

    public BufferedImage getAnimationFrame(BufferedImage[] spriteSheetRow) {
        return spriteSheetRow[currentFrame];
    }

    public void stopAnimation() {
        currentFrame = 0;
        animationTick = 0;
    }
}
