package dev.spnr.freed4j.message;

import dev.spnr.freed4j.util.FreeDBuffer;
import dev.spnr.freed4j.util.Metric;

public record PositionMessage(
        int camera, // 1 byte
        int pan, // 3 bytes, aka yaw
        int tilt, // 3 bytes, aka pitch
        int roll, // 3 bytes
        int x, // 3 bytes
        int y, // 3 bytes
        int z, // 3 bytes, Z+ up
        int zoom, // 3 bytes
        int focus, // 3 bytes
        short user // undefined
) implements Message {

    public PositionMessage(FreeDBuffer buffer) {
        this(
                buffer.readByte(), // camera
                buffer.readTrilobyte(), // pan
                buffer.readTrilobyte(), // tilt
                buffer.readTrilobyte(), // roll
                buffer.readTrilobyte(), // x
                buffer.readTrilobyte(), // y
                buffer.readTrilobyte(), // z
                buffer.readTrilobyteUnsigned(), // zoom
                buffer.readTrilobyteUnsigned(), // focus
                buffer.readShort() // user
        );
        buffer.readByte(); // TODO: Handle checksum
    }

    public Metric.MetricPosition metricPosition() {
        return new Metric.MetricPosition(this);
    }

}
