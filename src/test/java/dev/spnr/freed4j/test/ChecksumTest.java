package dev.spnr.freed4j.test;

import dev.spnr.freed4j.util.FreeDBuffer;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class ChecksumTest {

    @Test
    public void randomDataChecksum() {
        byte[] data = new byte[256];
        new Random(1).nextBytes(data);
        FreeDBuffer buffer = new FreeDBuffer(data);
        for (int i=0;i<data.length-1;i++) {
            buffer.writeByte(data[i]);
        }
        buffer.writeChecksum();
        byte writtenChecksum = data[data.length-1];
        buffer.position(0);
        buffer.readBytes(data.length-1);
        assertEquals(writtenChecksum, buffer.getExpectedChecksum());
    }

}
