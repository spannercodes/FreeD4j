package dev.spnr.freed4j.message;

import dev.spnr.freed4j.util.FreeDBuffer;

public record EEPROMMessage(
        int camera, // 1 byte
        short address,
        byte[] data // 16 bytes
) implements Message {

    public EEPROMMessage(FreeDBuffer buffer) {
        this(
                buffer.readByte(), // camera
                buffer.readShort(), // address
                buffer.readBytes(16) // data
        );
        buffer.readByte(); // TODO: Handle checksum
    }

}
