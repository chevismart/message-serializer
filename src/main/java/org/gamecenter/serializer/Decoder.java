package org.gamecenter.serializer;

import ch.qos.logback.core.encoder.ByteArrayUtil;
import org.gamecenter.serializer.constants.FieldType;
import org.gamecenter.serializer.messages.Field;
import org.gamecenter.serializer.messages.Message;
import org.gamecenter.serializer.messages.MessageHeader;
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
public class Decoder extends Codec {

    MessageLoader loader = MessageLoader.INSTANCE();
    private Logger logger = LoggerFactory.getLogger(Decoder.class);

    public List<Field> decode(byte[] msgBytes) throws IOException {
        List<Field> fields = new ArrayList<Field>();
        int pointer = 0;

        MessageHeader header = HeaderFilter.getMessageHeader(msgBytes, loader);

        if (isBodyLengthMatch(header, msgBytes)) {
            byte[] bytes = ByteUtil.subBytes(msgBytes, HeaderFilter.TOTAL_HEADER_LENGTH, header.getMsgBodyLength());


            short msgId = ByteUtil.getShort(header.getMessageId());

            Message message = loader.getMessageByMsgId(msgId);

            String displayMsgId = ByteArrayUtil.toHexString(ByteUtil.getMsgId(header.getMessageId()));
            logger.info("Message(id={}) = {}", displayMsgId, message);

            if (null == message) {
                throw new InvalidParameterException("The message(id={" + displayMsgId + "}) is not found");
            }

            List<Field> fieldList = message.getFields();
            for (Field field : fieldList) {
                byte[] fieldBytes = ByteUtil.subBytes(bytes, pointer, field.getLength());
                Field fieldVal = null;
                logger.debug("Read byte(s) value: {}", ByteArrayUtil.toHexString(fieldBytes));
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
                if (checkMandatory(fieldVal) && checkNull(field))
                    fields.add(fieldVal);
                pointer += field.getLength();
            }
        }

        return fields;
    }

    private boolean isBodyLengthMatch(MessageHeader header, byte[] bytes) {

        int requireLength = header.getMsgBodyLength();
        int actualLength = bytes.length - HeaderFilter.TOTAL_HEADER_LENGTH - HeaderFilter.END_FLAG_LENGTH;

        boolean result = requireLength == actualLength;

        if (!result) {
            throw new ArrayIndexOutOfBoundsException("Message body length is mismatch! Require: " + requireLength + ", Actual: " + actualLength);
        }

        return result;
    }

    private boolean isFieldType(FieldType fieldType, Field field) {
        return fieldType.toString().toLowerCase().equals(field.getType().toLowerCase());
    }

    private boolean checkMandatory(Field field) {
        boolean result = false;
        if (field.isMandatory() && null == field.getValue()) {
            throw new InvalidParameterException("Mandatory field is missed.");
        } else {
            result = true;
        }
        return result;
    }

    private boolean checkNull(Field field) {
        //TODO
        return true;
    }

}
