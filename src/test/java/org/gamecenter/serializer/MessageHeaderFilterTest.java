package org.gamecenter.serializer;

import org.gamecenter.serializer.messages.MessageHeader;
import org.gamecenter.serializer.messages.MessageLoader;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class MessageHeaderFilterTest {
    MessageHeader msgHeader;
    HeaderFilter filter;
    MessageLoader loader = MessageLoader.INSTANCE();

    @Test
    public void theMessageHeaderIsTooShortToBeDecoded() throws Exception {
        byte[] msg = new byte[]{0x2A, 0x01, 0x10, 0x04, 0x00, 0x06, 0x02, 0x06, 0x23};
        msgHeader = filter.getMessageHeader(msg, loader);
        assertNull(msgHeader);
    }

    @Test
    public void decodeMessageHeaderSuccessfully() throws Exception {
        byte[] msg = new byte[]{0x2A, 0x01, 0x00, 0x1a, 0x00, 0x01, 0x02, 0x03, 0x04, 0x01, 0x10, 0x00, 0x04, 0x06, 0x02, 0x06, 0x23};

        msgHeader = HeaderFilter.getMessageHeader(msg, loader);

        assertTrue(Arrays.equals((new byte[]{0x01, 0x00, 0x1a, 0x00}), msgHeader.getMessageSN()));

        assertTrue(Arrays.equals(new byte[]{0x01, 0x02, 0x03, 0x04}, msgHeader.getDeviceId()));

        assertTrue(Arrays.equals((new byte[]{0x01, 0x10}), msgHeader.getMessageId()));
        assertEquals(4, msgHeader.getMsgBodyLength());
    }

    @Test
    public void startFlagErrorCase() throws Exception {
        byte[] msg = new byte[]{0x2B, 0x01, 0x10, 0x04, 0x00, 0x06, 0x02, 0x06, 0x23};
        msgHeader = HeaderFilter.getMessageHeader(msg, loader);
        assertNull(msgHeader);
    }

    @Test
    public void endFlagErrorCase() throws Exception {
        byte[] msg = new byte[]{0x2A, 0x01, 0x10, 0x04, 0x00, 0x06, 0x02, 0x06, 0x22};
        msgHeader = HeaderFilter.getMessageHeader(msg, loader);
        assertNull(msgHeader);
    }
}