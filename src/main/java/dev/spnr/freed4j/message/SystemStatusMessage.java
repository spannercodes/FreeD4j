package dev.spnr.freed4j.message;

import dev.spnr.freed4j.util.FreeDBuffer;

public record SystemStatusMessage(
        int camera, // 1 byte
        byte switches,
        byte leds,
        byte status,
        byte cpuVersion,
        byte pldVersion,
        byte dspVersion,
        byte dspStatus,
        byte numTargetsSeen,
        byte numTargetsIdentified,
        byte numTargetsUsed,
        int rmsError // 3 bytes
) implements Message {

    public SystemStatusMessage(FreeDBuffer buffer) {
        this(
                buffer.readByte(), // camera
                buffer.readByte(), // switches
                buffer.readByte(), // LEDs
                buffer.readByte(), // status
                buffer.readByte(), // cpu version
                buffer.readByte(), // pld version
                buffer.readByte(), // dsp version
                buffer.readByte(), // dsp status
                buffer.readByte(), // num. targets seen
                buffer.readByte(), // num. targets identified
                buffer.readByte(), // num. targets used
                buffer.readTrilobyte() // rms error
        );
        buffer.readByte(); // TODO: Handle checksum
    }

}
