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

    byte[] macAddr = new byte[]{0x6c, (byte) 0xf0, 0x049, (byte) 0xb6, 0x4d, (byte) 0xf3};
    byte[] centerId = new byte[]{0x59, 0x00, 0x00, 0x00};
    byte[] messageSN = new byte[]{0x01, 0x00, 0x00, 0x00};
    byte[] deviceId = new byte[]{0x01, 0x02, 0x03, 0x04};
    byte[] requetByte = new byte[]{0x2a, 0x01, 0x00, 0x00, 0x00, 0x01, 0x02, 0x03, 0x04, 0x0A, 0x11, 0x0A, 0x00, 0x59, 0x00, 0x00, 0x00, 0x6c, (byte) 0xf0, 0x049, (byte) 0xb6, 0x4d, (byte) 0xf3, 0x00, 0x00, 0x23};

    @Test
    public void convertLoginRequestFromByteToObjectSuccessfully() throws Exception {

        LoginRequest request = new LoginRequest();

        request.parse(requetByte);

        assertNotNull(request);
        assertTrue(Arrays.equals(centerId, request.getCenterId()));
        assertTrue(Arrays.equals(messageSN, request.getHeader().getMessageSN()));
        assertTrue(Arrays.equals(deviceId, request.getHeader().getDeviceId()));
        assertTrue(Arrays.equals(macAddr, request.getMac()));
    }

    @Test
    public void convertLoginRequestFromObjectToByteSuccessfully() throws Exception {

        LoginRequest request = new LoginRequest();
        MessageHeader header = new MessageHeader();
        header.setDeviceId(deviceId);
        header.setMessageSN(messageSN);
        request.setHeader(header);
        request.setCenterId(centerId);
        request.setMac(macAddr);
        byte[] requestBytes = request.build();

        logger.info("The message from build = {}", ByteArrayUtil.toHexString(requestBytes));
        logger.info("The message should be  = {}", ByteArrayUtil.toHexString(requetByte));
        assertTrue(Arrays.equals(requestBytes, requetByte));
    }


}