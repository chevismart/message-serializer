package org.gamecenter.serializer.messages.upStream;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class LoginRequestTest {

    byte[] requetByte = new byte[]{0x2a, 0x01, 0x00, 0x00, 0x00, 0x01, 0x02, 0x03, 0x04, 0x01, 0x10, 0x04, 0x00, (byte) 0x99, 0x00, 0x00, 0x00, 0x23};

    @Test
    public void convertLoginRequestSuccessfully() throws Exception {

        LoginRequest request = new LoginRequest();

        request.parse(requetByte);

        assertNotNull(request);
        assertTrue(Arrays.equals(new byte[]{(byte) 0x99, 0x00, 0x00, 0x00}, request.getCenterId()));
        assertTrue(Arrays.equals(new byte[]{0x01, 0x00, 0x00, 0x00}, request.getHeader().getMessageSN()));
        assertTrue(Arrays.equals(new byte[]{0x01, 0x02, 0x03, 0x04}, request.getHeader().getDeviceId()));
    }


}