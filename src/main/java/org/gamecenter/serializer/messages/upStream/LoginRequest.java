package org.gamecenter.serializer.messages.upStream;

import org.gamecenter.serializer.messages.AbstractMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Boss on 2014/7/23.
 */
public class LoginRequest extends AbstractMessage {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private byte[] centerId;

    public LoginRequest(byte[] request) throws NoSuchFieldException, IllegalAccessException, IOException {
        buildMessage(request);
        logger.debug("The {} is instanced! {}", this.getClass().getName(), toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        LoginRequest request = (LoginRequest) o;

        if (!Arrays.equals(centerId, request.centerId)) return false;
        if (logger != null ? !logger.equals(request.logger) : request.logger != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (logger != null ? logger.hashCode() : 0);
        result = 31 * result + (centerId != null ? Arrays.hashCode(centerId) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LogonRequest{" +
                "centerId=" + Arrays.toString(centerId) +
                '}';
    }

    public byte[] getCenterId() {

        return centerId;
    }

    public void setCenterId(byte[] centerId) {
        this.centerId = centerId;
    }

    @Override
    public byte[] build() {
        return new byte[0];
    }
}
