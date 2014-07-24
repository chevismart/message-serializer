package org.gamecenter.serializer;

import org.gamecenter.serializer.constants.FieldType;
import org.gamecenter.serializer.messages.Field;
import org.gamecenter.serializer.messages.Message;
import org.gamecenter.serializer.messages.MessageLoader;
import org.gamecenter.serializer.utils.ByteUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chevis on 14-7-16.
 */
public class Decoder<T> extends Coder {

    MessageLoader factory = MessageLoader.INSTANCE();
    private Logger logger = LoggerFactory.getLogger(Decoder.class);

    public List<Field> decode(byte[] bytes) throws IOException {
        List<Field> fields = new ArrayList<Field>();
        int pointer = 0;

        if (bytes.length < MINIMUM_MSG_SIZE) {
            throw new InvalidParameterException("Message is too short to be decoded. Message size(" + bytes.length + ") is smaller than the minimum size(" + MINIMUM_MSG_SIZE + ").");
        }

        int msgId = ByteUtil.getMsgId(ByteUtil.subBytes(bytes, pointer, MINIMUM_MSG_SIZE));
        logger.info("Message ID = {}", msgId);
        pointer += MSG_TYPE_LENGTH;

        int msgBodyLength = ByteUtil.getShort(ByteUtil.subBytes(bytes, pointer, MSG_SIZE_LENGTH));
        logger.info("Message body length = {}", msgBodyLength);
        pointer += MSG_SIZE_LENGTH;

        if (isLengthMatch(msgBodyLength, bytes.length - MINIMUM_MSG_SIZE)) {
            logger.info("Message body length is Match!");


            Message message = factory.getMessage(msgId);
            logger.info("Message(id={}) is retrieved: {}", msgId, message);


            List<Field> fieldList = message.getFields();
            for (Field field : fieldList) {
                byte[] fieldBytes = ByteUtil.subBytes(bytes, pointer, field.getLength());
                Field fieldVal = null;
                logger.debug("Read byte(s) value: {}", fieldBytes);
                if (isFieldType(FieldType.BYTE, field)) {
                    fieldVal = new Field<Byte>(field);
                    fieldVal.setValue(fieldBytes[0]);

                } else if (isFieldType(FieldType.BYTES, field)) {
                    fieldVal = new Field<Byte[]>(field);
                    fieldVal.setValue(fieldBytes);

                } else if (isFieldType(FieldType.STRING, field)) {
                    fieldVal = new Field<String>(field);
                    fieldVal.setValue(ByteUtil.getString(fieldBytes));

                } else if (isFieldType(FieldType.WORD, field)) {
                    fieldVal = new Field<Integer>(field);
                    fieldVal.setValue(ByteUtil.getShort(fieldBytes));

                } else if (isFieldType(FieldType.DWOARD, field)) {
                    fieldVal = new Field<Long>(field);
                    fieldVal.setValue(ByteUtil.getInteger(fieldBytes));
                }
                fields.add(fieldVal);
                pointer += field.getLength();
            }
        } else {
            logger.error("Message body length is not match!");
        }

        return fields;
    }

    private boolean isLengthMatch(int expectedMsgBodyLength, int exactMsgBodyLength) {
        return expectedMsgBodyLength == exactMsgBodyLength;
    }

    private boolean isFieldType(FieldType fieldType, Field field) {
        return fieldType.toString().toLowerCase().equals(field.getType().toLowerCase());
    }

}
