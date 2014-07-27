package org.gamecenter.serializer;

import org.gamecenter.serializer.constants.MessageType;
import org.gamecenter.serializer.messages.Message;
import org.gamecenter.serializer.messages.MessageHeader;
import org.gamecenter.serializer.messages.MessageLoader;
import org.gamecenter.serializer.utils.ByteUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Chevis on 14-7-24.
 */
public class HeaderFilter {

    private final static int START_FLAG_LENGTH = 1;
    private final static int MSG_SEQUENCE_LENGTH = 4;
    private final static int DEVICE_ID_LENGTH = 4;
    private final static int MESSAGE_TYPE_LENGTH = 2;
    private final static int DATA_LENGTH = 2;
    public final static int TOTAL_HEADER_LENGTH = START_FLAG_LENGTH + MSG_SEQUENCE_LENGTH + DEVICE_ID_LENGTH + MESSAGE_TYPE_LENGTH + DATA_LENGTH;
    private final static int END_FLAG_LENGTH = 1;
    private final static Logger logger = LoggerFactory.getLogger(HeaderFilter.class);

    private final static byte START_FLAG = 0x2A;
    private final static byte END_FLAG = 0x23;

    public static MessageHeader getMessageHeader(byte[] message, MessageLoader loader) {

        MessageHeader msgHeader = null;

        if (START_FLAG == message[0] && END_FLAG == message[message.length - 1]) {

            if (message.length >= TOTAL_HEADER_LENGTH + END_FLAG_LENGTH) {
                msgHeader = new MessageHeader();

                int startPosition = START_FLAG_LENGTH;

                msgHeader.setMessageSN(ByteUtil.subBytes(message, startPosition, MSG_SEQUENCE_LENGTH));
                startPosition += MSG_SEQUENCE_LENGTH;

                msgHeader.setDeviceId(ByteUtil.subBytes(message, startPosition, DEVICE_ID_LENGTH));
                startPosition += DEVICE_ID_LENGTH;

                msgHeader.setMessageId(ByteUtil.subBytes(message, startPosition, MESSAGE_TYPE_LENGTH));
                startPosition += DATA_LENGTH;

                msgHeader.setMsgBodyLength(ByteUtil.getShort(ByteUtil.subBytes(message, startPosition, DATA_LENGTH)));

                Message msg = loader.getMessage(ByteUtil.getShort(msgHeader.getMessageId()));
                if (null != msg && null != MessageType.valueOf(msg.getName())) {
                    msgHeader.setMsgType(MessageType.valueOf(msg.getName()));
                } else {
                    logger.error("No message definition found for messageId = {}.", msgHeader.getMessageId());
                }

            } else {
                logger.error("The header is too short to be decoded.");
            }
        } else {
            logger.error("The message start or end flag is not match");
        }
        return msgHeader;
    }

}
