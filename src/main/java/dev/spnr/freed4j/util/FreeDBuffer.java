package dev.spnr.freed4j.util;

import java.nio.ByteBuffer;

public final class FreeDBuffer  {

    private final ByteBuffer byteBuffer;
    private byte readChecksum = 0x40;
    private byte writeChecksum = 0x40;
    public FreeDBuffer(byte[] array) {
        this.byteBuffer = ByteBuffer.wrap(array);
    }

    public byte readByte() {
        byte b = byteBuffer.get();
        readChecksum -= b;
        return b;
    }

    public short readShort() {
        byte h = readByte();
        byte l = readByte();
        return (byte) ((h << 8) | l);
    }

    public int readTrilobyteUnsigned() {
        byte h = readByte();
        byte m = readByte();
        byte l = readByte();
        return ((h << 16) & 0xFF) | ((m << 8) & 0xFF) | (l & 0xFF);
    }

    public int readTrilobyte() { // This is what I'm calling int24. Feel free to be mad at me.
        byte h = readByte();
        byte m = readByte();
        byte l = readByte();
        return ((h << 16)) | ((m << 8) & 0xFF) | (l & 0xFF);
    }

    public int readInt() {
        byte g = readByte();
        byte h = readByte();
        byte m = readByte();
        byte l = readByte();
        return (g << 24) | (h << 16) | (m << 8) | (l);
    }

    public byte[] readBytes(int n) {
        byte[] d = new byte[n];
        for (int i=0;i<n;i++) {
            d[i] = readByte();
        }
        return d;
    }

    public void writeByte(byte b) {
        byteBuffer.put(b);
        writeChecksum -= b;
    }

    public void writeShort(short s) {
        byte h = (byte) (s >> 8);
        byte l = (byte) (s);
        writeByte(h);
        writeByte(l);
    }

    public void writeTrilobyteUnsigned(int t) {
        byte h = (byte) ((t >> 16) & 0xFF);
        byte m = (byte) ((t >> 8) & 0xFF);
        byte l = (byte) ((t) & 0xFF);
        writeByte(h);
        writeByte(m);
        writeByte(l);
    }

    public void writeTrilobyte(int t) {
        byte h = (byte) ((t >> 16));
        byte m = (byte) ((t >> 8) & 0xFF);
        byte l = (byte) ((t) & 0xFF);
        writeByte(h);
        writeByte(m);
        writeByte(l);
    }

    public void writeInt(int i) {
        writeByte((byte) (i >> 24));
        writeByte((byte) (i >> 16));
        writeByte((byte) (i >> 8));
        writeByte((byte) i);
    }

    public byte getExpectedChecksum() {
        return readChecksum;
    }

    public void writeChecksum() {
        writeByte(writeChecksum);
    }

    public FreeDBuffer position(int index) {
        byteBuffer.position(index);
        return this;
    }

    public FreeDBuffer resetChecksum() {
        readChecksum = 0x40;
        writeChecksum = 0x40;
        return this;
    }

    public FreeDBuffer back(int n) {
        // TODO: Handle checksum
        int p = byteBuffer.position()-n;
        assert p >= 0;
        byteBuffer.position(p);
        return this;
    }

}
