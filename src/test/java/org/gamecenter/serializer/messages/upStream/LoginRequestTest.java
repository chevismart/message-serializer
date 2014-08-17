package org.gamecenter.serializer.messages.upStream;

import ch.qos.logback.core.encoder.ByteArrayUtil;
import org.gamecenter.serializer.messages.MessageHeader;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class LoginRequestTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    byte[] requetByte = new byte[]{0x2a, 0x01, 0x00, 0x00, 0x00, 0x01, 0x02, 0x03, 0x04, 0x01, 0x10, 0x04, 0x00,  0x59, 0x00, 0x00, 0x00,0x00, 0x00, 0x23};

    @Test
    public void convertLoginRequestFromByteToObjectSuccessfully() throws Exception {

        LoginRequest request = new LoginRequest();

        request.parse(requetByte);

        assertNotNull(request);
        assertTrue(Arrays.equals(new byte[]{0x59, 0x00, 0x00, 0x00}, request.getCenterId()));
        assertTrue(Arrays.equals(new byte[]{0x01, 0x00, 0x00, 0x00}, request.getHeader().getMessageSN()));
        assertTrue(Arrays.equals(new byte[]{0x01, 0x02, 0x03, 0x04}, request.getHeader().getDeviceId()));
    }

    @Test
    public void convertLoginRequestFromObjectToByteSuccessfully() throws Exception {

        LoginRequest request = new LoginRequest();
        MessageHeader header = new MessageHeader();
        header.setDeviceId(new byte[]{0x12, 0x13, (byte) 0xa5, (byte) 0xff});

        header.setMessageSN(new byte[]{0x02, 0x00, 0x00, 0x00});
        request.setHeader(header);

        byte[] requestBytes = request.build();

        System.err.println(this.getClass()+" in bytes is: "+ ByteArrayUtil.toHexString(requestBytes));
    }



}