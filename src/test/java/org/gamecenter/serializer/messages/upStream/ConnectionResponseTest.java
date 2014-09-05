package org.gamecenter.serializer.messages.upStream;

import ch.qos.logback.core.encoder.ByteArrayUtil;
import org.gamecenter.serializer.messages.MessageHeader;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ConnectionResponseTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    byte[] messageSN = new byte[]{0x01, 0x00, 0x00, 0x00};
    byte[] deviceId = new byte[]{0x01, 0x02, 0x03, 0x04};
    byte[] responseByte = new byte[]{0x2a, 0x01, 0x00, 0x00, 0x00, 0x01, 0x02, 0x03, 0x04, 0x09, 0x11, 0x0b, 0x00, 0x19, (byte) 0xC0, (byte) 0XA8, 0X00, 0X02, 0X6C, (byte) 0XF0, 0X49, (byte) 0XB6, 0X4D, (byte) 0XF3, 0x00, 0x00, 0x23};
    byte[] ip = new byte[]{(byte) 0xC0, (byte) 0xA8, 0x00, 0x02};
    byte[] macAddr = new byte[]{0x6c, (byte) 0xf0, 0x49, (byte) 0xb6, 0x4d, (byte) 0xf3};
    byte wifiSignal = 0x19;


    @Test
    public void convertConnectionResponseFromByteToObjectSuccessfully() throws Exception {
        ConnectionResponse ConnectionResponse = new ConnectionResponse();
        ConnectionResponse.parse(responseByte);
        assertNotNull(ConnectionResponse);
        assertTrue(Arrays.equals(messageSN, ConnectionResponse.getHeader().getMessageSN()));
        assertTrue(Arrays.equals(deviceId, ConnectionResponse.getHeader().getDeviceId()));
    }

    @Test
    public void convertByteToConnectionResponseSuccessfully() throws Exception {
        ConnectionResponse response = new ConnectionResponse();
        MessageHeader header = new MessageHeader();
        header.setDeviceId(deviceId);
        header.setMessageSN(messageSN);
        response.setHeader(header);

        response.setWifiSignal(wifiSignal);
        response.setIpAdd(ip);
        response.setMac(macAddr);

        byte[] requestBytes = response.build();

        logger.info("The message from build = {}", ByteArrayUtil.toHexString(requestBytes));
        logger.info("The message should be  = {}", ByteArrayUtil.toHexString(responseByte));
        assertTrue(Arrays.equals(requestBytes, responseByte));
    }

}