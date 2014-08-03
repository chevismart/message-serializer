package org.gamecenter.serializer.messages.downStream;

import org.gamecenter.serializer.messages.MessageHeader;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class LoginResponseTest {

    @Test
    public void convertLoginResponseSuccessfully() throws Exception {

        LoginResponse response = new LoginResponse();

        MessageHeader header = new MessageHeader();
        header.setDeviceId(new byte[]{0x12, 0x13, (byte) 0xa5, (byte) 0xff});

        header.setMessageSN(new byte[]{0x02, 0x00, 0x00, 0x00});

        response.setHeader(header);

        byte[] resp = response.build();

        assertNotNull(resp);

    }
}