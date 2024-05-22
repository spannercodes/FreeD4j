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
                buffer.readByte(), // whiteVideoClipLevel
                buffer.readByte(), // maxBlackPixels
                buffer.readByte() // maxWhitePixels
        );
        assert buffer.getExpectedChecksum() == buffer.readByte() : "Checksum match failed";
    }

    public void write(FreeDBuffer buffer) {
        buffer.writeByte(type());
        buffer.writeByte((byte) camera());
        buffer.writeByte((byte) studio);
        buffer.writeByte(smoothing);
        buffer.writeByte(maxAsymmetry);
        buffer.writeByte(halfBoxWidth);
        buffer.writeByte(blackVideoThreshold);
        buffer.writeByte(whiteVideoThreshold);
        buffer.writeByte(blackVideoClipLevel);
        buffer.writeByte(whiteVideoClipLevel);
        buffer.writeByte(maxBlackPixels);
        buffer.writeByte(maxWhitePixels);
        buffer.writeChecksum();
    }

    public byte type() {
        return (byte) 0xD3;
    }

}
