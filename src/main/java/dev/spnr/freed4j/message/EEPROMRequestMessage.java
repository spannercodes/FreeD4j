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
        assert buffer.getExpectedChecksum() == buffer.readByte() : "Checksum match failed";
    }

    public void write(FreeDBuffer buffer) {
        buffer.writeByte(type());
        buffer.writeByte((byte) camera());
        buffer.writeShort(address);
        buffer.writeChecksum();
    }

    public byte type() {
        return (byte) 0xD9;
    }

}
