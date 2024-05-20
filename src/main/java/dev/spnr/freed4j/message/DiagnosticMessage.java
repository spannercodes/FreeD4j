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
        buffer.readByte(); // TODO: Handle checksum
    }

}
