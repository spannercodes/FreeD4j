package dev.spnr.freed4j;

import dev.spnr.freed4j.message.*;
import dev.spnr.freed4j.util.FreeDBuffer;
import dev.spnr.freed4j.util.ObjectArray;

import java.util.function.Function;

public final class FreeD4j {

    private static final ObjectArray<Function<FreeDBuffer,Message>> MESSAGE_READERS = new ObjectArray<>(256);
    static {
        MESSAGE_READERS.set(0xD0, CommandMessage::new);
        MESSAGE_READERS.set(0xD1, PositionMessage::new);
        MESSAGE_READERS.set(0xD2, SystemStatusMessage::new);
        MESSAGE_READERS.set(0xD3, ParametersMessage::new);
        MESSAGE_READERS.set(0xD4, TargetMessage::new); // First Target
        MESSAGE_READERS.set(0xD5, TargetMessage::new); // Next Target
        MESSAGE_READERS.set(0xD6, ImageMessage::new); // First Image
        MESSAGE_READERS.set(0xD7, ImageMessage::new); // Next Image
        MESSAGE_READERS.set(0xD8, EEPROMMessage::new);
        MESSAGE_READERS.set(0xD9, EEPROMRequestMessage::new);
        MESSAGE_READERS.set(0xDA, CalibrationMessage::new);
        MESSAGE_READERS.set(0xDB, DiagnosticMessage::new);
    }

    public static Message readMessage(FreeDBuffer buffer) {
        byte type = buffer.readByte();
        Function<FreeDBuffer,Message> reader = MESSAGE_READERS.get(type);
        return reader.apply(buffer);
    }

    public static Message readMessage(byte[] payload) {
        return readMessage(new FreeDBuffer(payload));
    }

}
