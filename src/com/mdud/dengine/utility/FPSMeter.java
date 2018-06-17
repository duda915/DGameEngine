package com.mdud.dengine.utility;

public class FPSMeter {
    private static Double measurement = 0D;

    public static void measure(long before, long after) {
        measurement = 1000000000D / (after - before);
    }

    public static Double getMeasurement() {
        return measurement;
    }
}
