package org.gamecenter.serializer.utils;

import ch.qos.logback.core.encoder.ByteArrayUtil;
import org.gamecenter.serializer.messages.Field;
import org.gamecenter.serializer.messages.upStream.LogonRequest;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;

import java.util.List;

import static org.junit.Assert.*;

public class DecoderTest {

    @Test
    public void testBytesMsgIsShorterThanMinimumSize() throws Exception {

byte[] bytes = new byte[]{0x01,0x00,0x00,0x00,0x06,0x02,0x06,0x02};
        Decoder<LogonRequest> decoder = new Decoder<LogonRequest>();
        List<Field> fieldList = decoder.decode(bytes);
        LogonRequest request = new LogonRequest(fieldList);

        System.err.println(ByteArrayUtil.toHexString(request.getCenterId()));
    }

    @Test
    public void testBytesMessageIsEqualOrLongerThanMinimumSize() throws Exception {

    }

    @Test
    public void testDecoderCanDecodeTheBytesMsgSuccessfully() throws Exception {


    }

    @Test
    public void testDecoderFailsDecodeBytesMsg() throws Exception {


    }
}