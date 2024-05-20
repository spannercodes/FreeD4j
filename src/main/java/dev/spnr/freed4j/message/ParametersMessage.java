package dev.spnr.freed4j.message;

import dev.spnr.freed4j.util.FreeDBuffer;

// Worth noting that practically all of these fields are irrelevant to modern systems using
// this purely as a standard protocol for position data transfer.
public record ParametersMessage(
        int camera, // 1 byte
        int studio, // 1 byte, unsigned
        byte smoothing,
        byte maxAsymmetry,
        byte halfBoxWidth,
        byte blackVideoThreshold,
        byte whiteVideoThreshold,
        byte blackVideoClipLevel,
        byte whiteVideoClipLevel,
        byte maxBlackPixels,
        byte maxWhitePixels
) implements Message {

    public ParametersMessage(FreeDBuffer buffer) {
        this(
                buffer.readByte(), // camera
                buffer.readByte(), // studio
                buffer.readByte(), // smoothing
                buffer.readByte(), // maxAsymmetry
                buffer.readByte(), // halfBoxWidth
                buffer.readByte(), // blackVideoThreshold
                buffer.readByte(), // whiteVideoThreshold
                buffer.readByte(), // blackVideoClipLevel
                buffer.readByte(), // whiteVideoCiipLevel
                buffer.readByte(), // maxBlackPixels
                buffer.readByte() // maxWhitePixels
        );
        buffer.readByte(); // TODO: Handle checksum
    }

}
