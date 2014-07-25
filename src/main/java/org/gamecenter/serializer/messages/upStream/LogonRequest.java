package org.gamecenter.serializer.messages.upStream;

import org.gamecenter.serializer.Decoder;
import org.gamecenter.serializer.messages.Message;

import java.io.IOException;

/**
 * Created by Boss on 2014/7/23.
 */
public class LogonRequest extends Message implements Request {
    private byte[] centerId;

    public LogonRequest(byte[] request, Decoder decoder) throws NoSuchFieldException, IllegalAccessException, IOException {
        buildMessage(request, decoder);
    }

    public byte[] getCenterId() {

        return centerId;
    }

    public void setCenterId(byte[] centerId) {
        this.centerId = centerId;
    }

    @Override
    public void submit() {

    }
}
