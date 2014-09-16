package org.gamecenter.serializer.constants;

/**
 * Created by Chevis on 14-7-26.
 */
public enum MessageType {
    LoginRequest("LoginRequest"),
    LoginResponse("LoginResponse"),
    PowerStatusRequest("PowerStatusRequest"),
    PowerStatusResponse("PowerStatusResponse"),
    PowerControlRequest("PowerControlRequest"),
    HeartbeatRequest("HeartbeatRequest"),
    HeartbeatResponse("HeartbeatResponse"),
    ConnectionRequest("ConnectionRequest"),
    ConnectionResponse("ConnectionResponse"),
    CounterRequest("CounterRequest"),
    CounterResponse("CounterResponse"),
    TopUpRequest("TopUpRequest"),
    TopUpResponse("TopUpResponse"),
    ResetCounterRequest("ResetCounterRequest"),
    ResetCounterResponse("ResetCounterResponse"),
    RuntimeRequest("RuntimeRequest"),
    RuntimeResponse("RuntimeResponse"),
    CounterSwitchRequest("CounterSwitchRequest"),
    CounterStatusRequest("CounterStatusRequest"),
    CounterStatusResponse("CounterStatusResponse");

    private final String value;

    private MessageType(String value) {
        this.value = value;
    }
}
