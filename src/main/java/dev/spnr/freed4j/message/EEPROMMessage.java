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
        assert buffer.getExpectedChecksum() == buffer.readByte() : "Checksum match failed";
    }

    public void write(FreeDBuffer buffer) {
        buffer.writeByte(type());
        buffer.writeByte((byte) camera());
        buffer.writeShort(address);
        for (int i=0;i<16;i++) {
            buffer.writeByte(data[i]); // TODO: Add writeBytes(...)
        }
        buffer.writeChecksum();
    }

    public byte type() {
        return (byte) 0xD8;
    }

}
