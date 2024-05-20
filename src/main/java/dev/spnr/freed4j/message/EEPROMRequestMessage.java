package dev.spnr.freed4j.message;

import dev.spnr.freed4j.util.FreeDBuffer;

public record EEPROMRequestMessage(
        int camera, // 1 byte
        short address
) implements Message {

    public EEPROMRequestMessage(FreeDBuffer buffer) {
        this(
                buffer.readByte(), // camera
                buffer.readShort() // address
        );
        buffer.readByte(); // TODO: Handle checksum
    }

}
