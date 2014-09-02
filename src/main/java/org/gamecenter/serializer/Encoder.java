package org.gamecenter.serializer;

import ch.qos.logback.core.encoder.ByteArrayUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.gamecenter.serializer.messages.AbstractMessage;
import org.gamecenter.serializer.messages.Field;
import org.gamecenter.serializer.messages.Message;
import org.gamecenter.serializer.messages.MessageLoader;
import org.gamecenter.serializer.utils.ByteUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Chevis on 14-7-16.
 */
public class Encoder extends Codec {

    MessageLoader loader = MessageLoader.INSTANCE();
    private Logger logger = LoggerFactory.getLogger(Decoder.class);

    public byte[] encode(AbstractMessage message) throws Exception {
        byte[] byteMsg = new byte[0];

        Message msg = loadMessageDefinition(message.getClass().getSimpleName());
        List<Field> fieldList = msg.getFields();
        logger.debug("There are {} field(s) to be encoded.", fieldList.size());

        byteMsg = assembleByteArray(byteMsg, START_FLAG);
        if (Codec.MSG_SEQUENCE_LENGTH == message.getHeader().getMessageSN().length) {
            byteMsg = assembleByteArray(byteMsg, message.getHeader().getMessageSN());
        } else {
            logger.error("Length of message sequence is not valid.");
        }
        if (Codec.DEVICE_ID_LENGTH == message.getHeader().getDeviceId().length) {
            byteMsg = assembleByteArray(byteMsg, message.getHeader().getDeviceId());
        } else {
            logger.error("Length of device id is not valid");
        }

        byteMsg = assembleByteArray(byteMsg, ByteUtil.getMessageId(msg.getId()));

        byteMsg = assembleByteArray(byteMsg, ByteUtil.getBytes((short) msg.getMaxLength()));

        for (Field field : msg.getFields()) {
            logger.debug("Field [{}] to be encoded.", field.getName());
            byteMsg = assembleByteArray(byteMsg, getBytes(field, message));
        }

        //TODO: check null for message header

        // Assemble tailer segment
        byteMsg = assembleByteArray(byteMsg, getTailer());

        logger.debug("The final byte array is = {}", ByteArrayUtil.toHexString(byteMsg));

        return byteMsg;
    }

    private byte[] getBytes(Field field, AbstractMessage message) throws Exception {
// TODO: to verify each type of the field value conversion.
        if (field.getType().toLowerCase().equals(String.class.getSimpleName().toLowerCase())) {
            return ByteUtil.getBytes((String) message.getFieldValue(field.getName()));
        } else if (field.getType().toLowerCase().equals(Byte.class.getSimpleName().toLowerCase() + "s")) {
            return (byte[]) message.getFieldValue(field.getName());
        } else {
            byte[] emptyField = new byte[field.getLength()];
            for (int i = 0; i < field.getLength(); i++) {
                emptyField[i] = 0;
            }
        }

        return new byte[0];
    }

    private byte[] assembleByteArray(byte[] sourceByteArray, byte byteVar) {
        return ArrayUtils.add(sourceByteArray, byteVar);
    }

    private byte[] assembleByteArray(byte[] sourceByteArray, byte[] valueByteArray) {
        return ArrayUtils.addAll(sourceByteArray, valueByteArray);
    }

    private Message loadMessageDefinition(String msgName) {
        return loader.getMessageByName(msgName);
    }

}
