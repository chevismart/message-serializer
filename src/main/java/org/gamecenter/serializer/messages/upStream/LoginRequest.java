package org.gamecenter.serializer.messages.upStream;

import ch.qos.logback.core.encoder.ByteArrayUtil;
import org.gamecenter.serializer.messages.AbstractMessage;

/**
 * Created by Boss on 2014/7/23.
 */
public class LoginRequest extends AbstractMessage {

    private byte[] centerId;

    @Override
    public String toString() {
        return "LogonRequest{" +
                "centerId=" + ByteArrayUtil.toHexString(centerId) +
                '}';
    }

    public byte[] getCenterId() {

        return centerId;
    }

    public void setCenterId(byte[] centerId) {
        this.centerId = centerId;
    }

}
