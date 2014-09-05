package org.gamecenter.serializer.messages.upStream;

import ch.qos.logback.core.encoder.ByteArrayUtil;
import org.gamecenter.serializer.messages.MessageHeader;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class HeartbeatRequestTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    byte[] messageSN = new byte[]{0x01, 0x00, 0x00, 0x00};
    byte[] deviceId = new byte[]{0x01, 0x02, 0x03, 0x04};
    byte[] requetByte = new byte[]{0x2a, 0x01, 0x00, 0x00, 0x00, 0x01, 0x02, 0x03, 0x04, 0x0B, 0x11, 0x00, 0x00, 0x00, 0x00, 0x23};


    @Test
    public void convertHeartbeatRequestFromByteToObjectSuccessfully() throws Exception {
        HeartbeatRequest heartbeatRequest = new HeartbeatRequest();
        heartbeatRequest.parse(requetByte);
        assertNotNull(heartbeatRequest);
        assertTrue(Arrays.equals(messageSN, heartbeatRequest.getHeader().getMessageSN()));
        assertTrue(Arrays.equals(deviceId, heartbeatRequest.getHeader().getDeviceId()));
    }

    @Test
    public void convertByteToHeartbeatRequestSuccessfully() throws Exception {
        HeartbeatRequest request = new HeartbeatRequest();
        MessageHeader header = new MessageHeader();
        header.setDeviceId(deviceId);
        header.setMessageSN(messageSN);
        request.setHeader(header);
        byte[] requestBytes = request.build();

        logger.info("The message from build = {}", ByteArrayUtil.toHexString(requestBytes));
        logger.info("The message should be  = {}", ByteArrayUtil.toHexString(requetByte));
        assertTrue(Arrays.equals(requestBytes, requetByte));
    }
}