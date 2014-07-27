package org.gamecenter.serializer.constants;

/**
 * Created by Chevis on 14-7-26.
 */
public enum MessageType {
    LoginRequest("LoginRequest");

    private final String value;

    private MessageType(String value) {
        this.value = value;
    }
}
