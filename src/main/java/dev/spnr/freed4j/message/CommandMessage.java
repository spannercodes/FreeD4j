package dev.spnr.freed4j.message;

import dev.spnr.freed4j.util.FreeDBuffer;

public record CommandMessage(
        int camera, // 1 byte
        byte command
) implements Message {

    public CommandMessage(FreeDBuffer buffer) {
        this(
                buffer.readByte(), // camera
                buffer.readByte() // command
        );
        buffer.readByte(); // TODO: Handle checksum
    }

    public CommandMessage stopStreamMode(int camera) {
        return new CommandMessage(camera, (byte)0x00);
    }

    public CommandMessage startStreamMode(int camera) {
        return new CommandMessage(camera, (byte)0x01);
    }

    public CommandMessage stopFreezeMode(int camera) {
        return new CommandMessage(camera, (byte)0x02);
    }

    public CommandMessage startFreezeMode(int camera) {
        return new CommandMessage(camera, (byte)0x03);
    }

    public CommandMessage pollPosition(int camera) {
        return new CommandMessage(camera, (byte)0xD1);
    }

    public CommandMessage requestSystemStatus(int camera) {
        return new CommandMessage(camera, (byte)0xD2);
    }

    public CommandMessage requestSystemParameters(int camera) {
        return new CommandMessage(camera, (byte)0xD3);
    }

    public CommandMessage requestFirstTarget(int camera) {
        return new CommandMessage(camera, (byte)0xD4);
    }

    public CommandMessage requestNextTarget(int camera) {
        return new CommandMessage(camera, (byte)0xD5);
    }

    public CommandMessage requestFirstImage(int camera) {
        return new CommandMessage(camera, (byte)0xD6);
    }

    public CommandMessage requestNextImage(int camera) {
        return new CommandMessage(camera, (byte)0xD7);
    }

    public CommandMessage requestNextEEPROM(int camera) {
        return new CommandMessage(camera, (byte)0xD8);
    }

    public CommandMessage requestCalibration(int camera) {
        return new CommandMessage(camera, (byte)0xDA);
    }

    public CommandMessage requestDiagnostic(int camera) {
        return new CommandMessage(camera, (byte)0xDB);
    }

}
