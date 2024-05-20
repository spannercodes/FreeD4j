package dev.spnr.freed4j.util;

import java.nio.ByteBuffer;

public final class FreeDBuffer  {

    private final ByteBuffer byteBuffer;
    public FreeDBuffer(byte[] array) {
        this.byteBuffer = ByteBuffer.wrap(array);
    }

    public byte readByte() {
        return byteBuffer.get();
    }

    public short readShort() {
        return byteBuffer.getShort();
    }

    public int readTrilobyte() { // This is what I'm calling int24. Feel free to be mad at me.
        byte h = byteBuffer.get();
        byte m = byteBuffer.get();
        byte l = byteBuffer.get();
        return (h << 16) | (m << 8) | (l);
    }

    public int readInt() {
        return byteBuffer.getInt();
    }

    public byte[] readBytes(int n) {
        byte[] d = new byte[n];
        byteBuffer.get(d);
        return d;
    }

}
