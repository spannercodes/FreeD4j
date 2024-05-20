package dev.spnr.freed4j.message;

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
}
