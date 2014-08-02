package org.gamecenter.serializer;

import ch.qos.logback.core.encoder.ByteArrayUtil;
import org.gamecenter.serializer.messages.downStream.LoginResponse;
import org.gamecenter.serializer.messages.downStream.PowerControlRequest;
import org.junit.Before;
import org.junit.Test;

public class EncoderTest {

    Encoder encoder;

    @Before
    public void setUp() throws Exception {
        encoder = new Encoder();
    }

    @Test
    public void encodeTheMessageWithOneFieldMessageSuccessfully() throws Exception {
        PowerControlRequest request = new PowerControlRequest(encoder);
        request.setDeviceId(new byte[]{0x01, 0x02, 0x03, 0x04});
        request.setSequenceNum(new byte[]{0x01, 0x00, 0x00, 0x00});
        request.setSwitcher("Y");
        byte[] byteArray = encoder.encode(request);
        System.err.println((ByteArrayUtil.toHexString(byteArray)));
    }

    @Test
    public void encodeAnEmptyFieldMessageSuccessfully() throws Exception {


        LoginResponse response = new LoginResponse(encoder);
        response.setDeviceId(new byte[]{0x01, 0x02, 0x03, 0x04});
        response.setSequenceNum(new byte[]{0x01, 0x00, 0x00, 0x00});

        byte[] byteArray = encoder.encode(response);
        System.err.println((ByteArrayUtil.toHexString(byteArray)));
    }
}