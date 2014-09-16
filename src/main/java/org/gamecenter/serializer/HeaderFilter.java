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
public class HeaderFilter extends Codec {

    private final static Logger logger = LoggerFactory.getLogger(HeaderFilter.class);


    public static MessageHeader getMessageHeader(byte[] message, MessageLoader loader) {

        MessageHeader msgHeader = null;

        if (START_FLAG == message[0]) {

            if (message.length >= TOTAL_HEADER_LENGTH) {
                msgHeader = new MessageHeader();

                int startPosition = START_FLAG_LENGTH;

                msgHeader.setMessageSN(ByteUtil.subBytes(message, startPosition, MSG_SEQUENCE_LENGTH));
                startPosition += MSG_SEQUENCE_LENGTH;

                msgHeader.setDeviceId(ByteUtil.subBytes(message, startPosition, DEVICE_ID_LENGTH));
                startPosition += DEVICE_ID_LENGTH;

                msgHeader.setMessageId(ByteUtil.subBytes(message, startPosition, MESSAGE_TYPE_LENGTH));
                startPosition += MESSAGE_TYPE_LENGTH;

                msgHeader.setMsgBodyLength(ByteUtil.getShort(ByteUtil.subBytes(message, startPosition, DATA_LENGTH)));


                String msgId = ByteUtil.getMessageId(msgHeader.getMessageId());

                try {
                    Message msg = loader.getMessageByMsgId(msgId);
                    msgHeader.setMsgType(MessageType.valueOf(msg.getName()));
                } catch (Exception e) {

//                }
//                if (null != msg && null != MessageType.valueOf(msg.getName())) {
//                } else {
                    logger.error("No message definition found for messageId = {}.", ByteUtil.getMessageId(msgHeader.getMessageId()));
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
