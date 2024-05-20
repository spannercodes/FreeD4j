package dev.spnr.freed4j.message;

import dev.spnr.freed4j.util.FreeDBuffer;

public record CalibrationMessage(
        int camera, // 1 byte
        int lensX, // 3 bytes
        int lensY, // 3 bytes
        int lensXScale, // 3 bytes
        int lensYScale, // 3 bytes
        int lensDistortionA, // 3 bytes ("radial, square term")
        int lensDistortionB, // 3 bytes ("radial, fourth power")
        int xOffset, // 3 bytes
        int yOffset, // 3 bytes
        int zOffset // 3 bytes
) implements Message {

    public CalibrationMessage(FreeDBuffer buffer) {
        this(
                buffer.readByte(), // camera
                buffer.readTrilobyte(), // lensX
                buffer.readTrilobyte(), // lensY
                buffer.readTrilobyte(), // lensXScale
                buffer.readTrilobyte(), // lensYScale
                buffer.readTrilobyte(), // lensDistortionA
                buffer.readTrilobyte(), // lensDistortionB
                buffer.readTrilobyte(), // xOffset
                buffer.readTrilobyte(), // yOffset
                buffer.readTrilobyte() // zOffset
        );
        buffer.readByte(); // TODO: Handle checksum
    }

}
