package dev.spnr.freed4j.message;

import dev.spnr.freed4j.util.FreeDBuffer;

public sealed interface Message permits
        CommandMessage,
        PositionMessage,
        SystemStatusMessage,
        ParametersMessage,
        TargetMessage,
        ImageMessage,
        EEPROMMessage,
        EEPROMRequestMessage,
        CalibrationMessage,
        DiagnosticMessage
{

    void write(FreeDBuffer buffer);

    byte type();
    int camera();

}
