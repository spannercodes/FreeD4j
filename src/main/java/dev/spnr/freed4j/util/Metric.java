package dev.spnr.freed4j.util;

import dev.spnr.freed4j.message.PositionMessage;

public final class Metric {

    public record MetricPosition(
            double pan, double tilt, double roll,
            double x, double y, double z
    ) {
        public MetricPosition(PositionMessage m) {
            this(
                convertAngle(m.pan()), convertAngle(m.tilt()), convertAngle(m.roll()),
                convertLength(m.x()), convertLength(m.y()), convertLength(m.z())
            );
        }
    }

    public static double convertAngle(int raw) { // Returns in degrees
        // raw is in units of 1/32768 degree
        return ((double) raw / 32768);
    }

    public static double convertLength(int raw) { // Returns in metres
        // raw is in units of 1/64 mm
        return ((double) raw / 64) * 1000;
    }

}
