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
        assert buffer.getExpectedChecksum() == buffer.readByte() : "Checksum match failed";
    }

    public void write(FreeDBuffer buffer) {
        buffer.writeByte(type());
        buffer.writeByte((byte) camera());
        buffer.writeTrilobyte(pan);
        buffer.writeTrilobyte(tilt);
        buffer.writeTrilobyte(roll);
        buffer.writeTrilobyte(x);
        buffer.writeTrilobyte(y);
        buffer.writeTrilobyte(z);
        buffer.writeTrilobyteUnsigned(zoom);
        buffer.writeTrilobyteUnsigned(focus);
        buffer.writeShort(user);
        buffer.writeChecksum();
    }

    public byte type() {
        return (byte) 0xD1;
    }

    public Metric.MetricPosition metricPosition() {
        return new Metric.MetricPosition(this);
    }

}
