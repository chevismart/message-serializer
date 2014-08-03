package org.gamecenter.serializer;

import ch.qos.logback.core.encoder.ByteArrayUtil;
import org.gamecenter.serializer.messages.MessageHeader;
import org.gamecenter.serializer.messages.downStream.LoginResponse;
import org.gamecenter.serializer.messages.downStream.PowerControlRequest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class EncoderTest {

    Encoder encoder;

    @Before
    public void setUp() throws Exception {
        encoder = new Encoder();
    }

    @Test
    public void encodeTheMessageWithOneFieldMessageSuccessfully() throws Exception {
        PowerControlRequest request = new PowerControlRequest();
        MessageHeader header = new MessageHeader();
        header.setDeviceId(new byte[]{0x01, 0x02, 0x03, 0x04});
        header.setMessageSN(new byte[]{0x01, 0x00, 0x00, 0x00});
        request.setHeader(header);
        request.setSwitcher("Y");
        byte[] byteArray = encoder.encode(request);
        assertNotNull(byteArray);
        System.err.println((ByteArrayUtil.toHexString(byteArray)));
    }

    @Test
    public void encodeAnEmptyFieldMessageSuccessfully() throws Exception {


        LoginResponse response = new LoginResponse();
        MessageHeader header = new MessageHeader();
        header.setDeviceId(new byte[]{0x01, 0x02, 0x03, 0x04});
        header.setMessageSN(new byte[]{0x01, 0x00, 0x00, 0x00});
        response.setHeader(header);
        byte[] byteArray = encoder.encode(response);
        System.err.println((ByteArrayUtil.toHexString(byteArray)));
    }
}