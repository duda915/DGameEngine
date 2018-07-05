package com.mdud.dengine.utility.timing;

public class FPSMeter {
    private static int measurementsCount = 0;
    private static Double measurement = 0D;
    private static Double stableMeasurement = 0D;
    private static Double stableMeasurementTemp = 0D;

    public static void measure(long before, long after) {
        measurement = 1000000000D / (after - before);
    }

    //After 20 measurements averages result to stableMeasurement
    public static void measureStable(long before, long after) {
        measurement = 1000000000D / (after - before);
        stableMeasurementTemp += measurement;
        measurementsCount++;
        if (measurementsCount == 20){
            stableMeasurement = stableMeasurementTemp / 20;
            stableMeasurementTemp = 0D;
            measurementsCount = 0;
        }
    }

    public static Double getMeasurement() {
        return measurement;
    }

    public static Double getStableMeasurement() {
        return stableMeasurement;
    }
}
