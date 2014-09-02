package org.gamecenter.serializer.messages.upStream;

import ch.qos.logback.core.encoder.ByteArrayUtil;
import org.gamecenter.serializer.messages.AbstractMessage;

/**
 * Created by Boss on 2014/7/23.
 */
public class LoginRequest extends AbstractMessage {

    private byte[] centerId;
    private byte[] mac;

    public byte[] getCenterId() {

        return centerId;
    }

    public void setCenterId(byte[] centerId) {
        this.centerId = centerId;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "centerId=" + ByteArrayUtil.toHexString(centerId) +
                ", mac=" + ByteArrayUtil.toHexString(mac) +
                '}';
    }

    public byte[] getMac() {
        return mac;
    }

    public void setMac(byte[] mac) {
        this.mac = mac;
    }
}
