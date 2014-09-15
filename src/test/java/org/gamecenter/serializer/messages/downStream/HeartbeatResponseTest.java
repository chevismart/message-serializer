package org.gamecenter.serializer.messages.downStream;

import ch.qos.logback.core.encoder.ByteArrayUtil;
import org.gamecenter.serializer.messages.MessageHeader;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class HeartbeatResponseTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    byte[] messageSN = new byte[]{0x01, 0x00, 0x00, 0x00};
    byte[] deviceId = new byte[]{0x01, 0x02, 0x03, 0x04};
    byte[] requetByte = new byte[]{0x2a, 0x01, 0x00, 0x00, 0x00, 0x01, 0x02, 0x03, 0x04, 0x10, 0x0D, 0x00, 0x00, 0x00, 0x00, 0x23};


    @Test
    public void convertHeartbeatResponseFromByteToObjectSuccessfully() throws Exception {
        HeartbeatResponse HeartbeatResponse = new HeartbeatResponse();
        HeartbeatResponse.parse(requetByte);
        assertNotNull(HeartbeatResponse);
        assertTrue(Arrays.equals(messageSN, HeartbeatResponse.getHeader().getMessageSN()));
        assertTrue(Arrays.equals(deviceId, HeartbeatResponse.getHeader().getDeviceId()));
    }

    @Test
    public void convertByteToHeartbeatResponseSuccessfully() throws Exception {
        HeartbeatResponse request = new HeartbeatResponse();
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