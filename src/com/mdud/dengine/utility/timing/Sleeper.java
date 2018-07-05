package com.mdud.dengine.utility.timing;


public class Sleeper {
    public static void sleep(int ms) {
        if (ms > 0) {
            try {
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
