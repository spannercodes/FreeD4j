package dev.spnr.freed4j.message;

import dev.spnr.freed4j.util.FreeDBuffer;

public record DiagnosticMessage(
        int camera, // 1 byte
        byte mode
) implements Message {

    public DiagnosticMessage(FreeDBuffer buffer) {
        this(
                buffer.readByte(), // camera
                buffer.readByte() // mode
        );
        assert buffer.getExpectedChecksum() == buffer.readByte() : "Checksum match failed";
    }

    public void write(FreeDBuffer buffer) {
        buffer.writeByte(type());
        buffer.writeByte((byte) camera());
        buffer.writeByte(mode);
        buffer.writeChecksum();
    }

    public byte type() {
        return (byte) 0xDB;
    }

}
