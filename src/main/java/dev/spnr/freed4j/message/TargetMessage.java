package dev.spnr.freed4j.message;

import dev.spnr.freed4j.util.FreeDBuffer;

// NOTE: D4 is first target, D5 is "next target"
public record TargetMessage(
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
                buffer.readByte(), // camera
                buffer.readByte(), // studio
                buffer.readShort(), // target
                buffer.readTrilobyte(), // x
                buffer.readTrilobyte(), // y
                buffer.readTrilobyte(), // z
                buffer.readTrilobyte() // flags
        );
    }

}
