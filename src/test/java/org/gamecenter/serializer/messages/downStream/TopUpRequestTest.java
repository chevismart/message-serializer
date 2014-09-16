package org.gamecenter.serializer.messages.downStream;

import ch.qos.logback.core.encoder.ByteArrayUtil;
import org.gamecenter.serializer.messages.MessageHeader;
import org.junit.Test;

public class TopUpRequestTest {

    byte[] messageSN = new byte[]{0x01, 0x00, 0x00, 0x00};
    byte[] deviceId = new byte[]{0x01, 0x02, 0x03, 0x04};
    byte[] message = ByteArrayUtil.hexStringToByteArray(new String("2a00000000000000011005000c414243444546303030310001000023"));

    private MessageHeader getMessageHeader() {
        MessageHeader header = new MessageHeader();
        header.setDeviceId(deviceId);
        header.setMessageSN(messageSN);
        return header;
    }

    @Test
    public void canTopUpCoinSuccessfully() throws Exception {


    }
}