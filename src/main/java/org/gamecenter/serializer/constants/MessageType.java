package org.gamecenter.serializer.constants;

import org.gamecenter.serializer.messages.upStream.HeartbeatRequest;

/**
 * Created by Chevis on 14-7-26.
 */
public enum MessageType {
    LoginRequest("LoginRequest"),
    LoginResponse("LoginResponse"),
    PowerControlRequest("PowerControlRequest"),
    HeartbeatRequest("HeartbeatRequest"),
    HeartbeatResponse("HeartbeatResponse"),
    ConnectionRequest("ConnectionRequest"),
    ConnectionResponse("ConnectionResponse");

    private final String value;

    private MessageType(String value) {
        this.value = value;
    }
}
