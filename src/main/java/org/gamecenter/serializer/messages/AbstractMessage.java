package org.gamecenter.serializer.messages;

import org.gamecenter.serializer.Decoder;

import java.io.IOException;
import java.util.List;

/**
 * Created by Chevis on 14-7-29.
 */
public abstract class AbstractMessage {

    protected byte[] deviceId;
    protected int msgBodyLength;

    public int getMsgBodyLength() {
        return msgBodyLength;
    }

    public void setMsgBodyLength(int msgBodyLength) {
        this.msgBodyLength = msgBodyLength;
    }

    public byte[] getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(byte[] deviceId) {
        this.deviceId = deviceId;
    }

    abstract public byte[] build();

    protected void buildMessage(byte[] request, Decoder decoder) throws IOException, NoSuchFieldException, IllegalAccessException {
        List<Field> fieldList = decoder.decode(request);

        for (Field field : fieldList) {
            java.lang.reflect.Field tmpField = this.getClass().getDeclaredField(field.getName());
            tmpField.setAccessible(true);
            tmpField.set(this, field.getValue());
            tmpField.setAccessible(false);
        }
    }

}
