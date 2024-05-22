package dev.spnr.freed4j.message;

import dev.spnr.freed4j.util.FreeDBuffer;

// NOTE: D4 is first target, D5 is "next target"
public record TargetMessage(
        byte type,
        int camera, // 1 byte
        int studio, // 1 byte
        short target, // target number
        int x, // 3 bytes
        int y, // 3 bytes
        int z, // 3 bytes, Z+ up
        int flags // 3 bytes
) implements Message {

    public TargetMessage(FreeDBuffer buffer) {
        this(
                buffer.back(1).resetChecksum().readByte(), // type
                buffer.readByte(), // camera
                buffer.readByte(), // studio
                buffer.readShort(), // target
                buffer.readTrilobyte(), // x
                buffer.readTrilobyte(), // y
                buffer.readTrilobyte(), // z
                buffer.readTrilobyte() // flags
        );
        assert buffer.getExpectedChecksum() == buffer.readByte() : "Checksum match failed";
    }

    public void write(FreeDBuffer buffer) {
        buffer.writeByte(type());
        buffer.writeByte((byte) camera());
        buffer.writeByte((byte) studio);
        buffer.writeShort(target);
        buffer.writeTrilobyte(x);
        buffer.writeTrilobyte(y);
        buffer.writeTrilobyte(z);
        buffer.writeTrilobyte(flags);
        buffer.writeChecksum();
    }

}
