package org.gamecenter.serializer.messages.downStream;

import ch.qos.logback.core.encoder.ByteArrayUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.gamecenter.serializer.messages.MessageHeader;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class LoginResponseTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    byte[] messageSN = new byte[]{0x01, 0x00, 0x00, 0x00};
    byte[] deviceId = new byte[]{0x01, 0x02, 0x03, 0x04};
    byte[] response = new byte[]{0x2a, 0x01, 0x00, 0x00, 0x00, 0x01, 0x02, 0x03, 0x04, 0x10, 0x0c, 0x00, 0x00, 0x00, 0x00, 0x23};


    @Test
    public void convertByteArraytoLoginResponseSuccessfully() throws Exception {

        LoginResponse response = new LoginResponse();
        MessageHeader header = new MessageHeader();
        header.setDeviceId(deviceId);
        header.setMessageSN(messageSN);
        response.setHeader(header);
        byte[] resp = response.build();

        logger.info("Expected: {}" , ByteArrayUtil.toHexString(this.response));
        logger.info("Actual:   {}" ,ByteArrayUtil.toHexString(resp));
        assertNotNull(resp);
        assertTrue(ArrayUtils.isEquals(this.response, resp));

    }

    @Test
    public void convertLoginResponseToByteArraySuccessfully() throws Exception {
        LoginResponse response = new LoginResponse();
        response.parse(this.response);
        assertNotNull(response);
        assertTrue(ArrayUtils.isEquals(response.getHeader().getDeviceId(),deviceId));
        assertTrue(ArrayUtils.isEquals(response.getHeader().getMessageSN(),messageSN));
    }
}