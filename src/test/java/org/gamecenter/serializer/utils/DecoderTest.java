package org.gamecenter.serializer.utils;

import ch.qos.logback.core.encoder.ByteArrayUtil;
import org.gamecenter.serializer.Decoder;
import org.gamecenter.serializer.messages.upStream.LogonRequest;
import org.junit.Test;

public class DecoderTest {

    Decoder decoder = new Decoder();

    @Test
    public void testBytesMsgIsShorterThanMinimumSize() throws Exception {

        byte[] bytes = new byte[]{0x01, 0x10, 0x04, 0x00, 0x06, 0x02, 0x06, 0x02};

        LogonRequest request = new LogonRequest(bytes, decoder);
        System.err.println(request.getCenterId());
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