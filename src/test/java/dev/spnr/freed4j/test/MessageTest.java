package dev.spnr.freed4j.test;

import dev.spnr.freed4j.FreeD4j;
import dev.spnr.freed4j.message.CalibrationMessage;
import dev.spnr.freed4j.util.FreeDBuffer;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class MessageTest {

    @Test
    public void readWriteMessage() {
        CalibrationMessage m = new CalibrationMessage(0,0,0,0,0,0,0,0,0,0);
        byte[] data = new byte[64];
        FreeDBuffer b = new FreeDBuffer(data);
        m.write(b);
        b.position(0).resetChecksum();
        CalibrationMessage m2 = (CalibrationMessage) FreeD4j.readMessage(data);
        assertEquals(m, m2);
    }

}
