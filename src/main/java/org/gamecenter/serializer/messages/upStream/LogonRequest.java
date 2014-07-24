package org.gamecenter.serializer.messages.upStream;

import org.gamecenter.serializer.messages.Field;

import java.util.List;

/**
 * Created by Boss on 2014/7/23.
 */
public class LogonRequest implements Request{
    private byte[] centerId;

    public LogonRequest(List<Field> fieldList) throws NoSuchFieldException, IllegalAccessException {
        for(Field field : fieldList){
            this.getClass().getDeclaredField(field.getName()).set(this,field.getValue());
        }
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
