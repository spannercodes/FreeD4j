package dev.spnr.freed4j.message;

import dev.spnr.freed4j.util.FreeDBuffer;

// NOTE: D6 is first target, D7 is "next target"
public record ImageMessage(
    byte type,
    int camera, // 1 byte
    byte targetIndex,
    short targetNumber,
    int x, // 3 bytes
    int y, // 3 bytes
    int xError, // 3 bytes
    int yError // 3 bytes
) implements Message {

    public ImageMessage(FreeDBuffer buffer) {
        this(
                buffer.back(1).resetChecksum().readByte(), // type
                buffer.readByte(), // camera
                buffer.readByte(), // targetIndex
                buffer.readShort(), // targetNumber
                buffer.readTrilobyte(), // x
                buffer.readTrilobyte(), // y
                buffer.readTrilobyte(), // xError
                buffer.readTrilobyte() // yError
        );
        assert buffer.getExpectedChecksum() == buffer.readByte() : "Checksum match failed";
    }

    public void write(FreeDBuffer buffer) {
        buffer.writeByte(type());
        buffer.writeByte((byte) camera());
        buffer.writeByte(targetIndex);
        buffer.writeShort(targetNumber);
        buffer.writeTrilobyte(x);
        buffer.writeTrilobyte(y);
        buffer.writeTrilobyte(xError);
        buffer.writeTrilobyte(yError);
        buffer.writeChecksum();
    }

}
