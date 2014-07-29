package org.gamecenter.serializer;

import ch.qos.logback.core.encoder.ByteArrayUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.gamecenter.serializer.messages.AbstractMessage;
import org.gamecenter.serializer.messages.Field;
import org.gamecenter.serializer.messages.Message;
import org.gamecenter.serializer.messages.MessageLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Chevis on 14-7-16.
 */
public class Encoder extends Coder {

    MessageLoader loader = MessageLoader.INSTANCE();
    private Logger logger = LoggerFactory.getLogger(Decoder.class);

    public byte[] encode(AbstractMessage message) {
        byte[] byteMsg = new byte[0];

        Message msg = loadMessageDefinition(message.getClass().getSimpleName());
        List<Field> fieldList = msg.getFields();
        logger.debug("There are {} field(s) to be encoded.", fieldList.size());

        byteMsg = assembleByteArray(byteMsg, START_FLAG);

        for (Field field : msg.getFields()) {
            logger.debug("Field {} to be encoded.", field.getName());
            byteMsg = Arrays.copyOf(byteMsg, byteMsg.length + field.getLength());
            byteMsg = ArrayUtils.addAll(byteMsg, getBytes(field));
        }

        //TODO: check null for message header

        byteMsg = assembleByteArray(byteMsg, END_FLAG);

        logger.debug("The final byte array is = {}", ByteArrayUtil.toHexString(byteMsg));

        return byteMsg;
    }

    private byte[] getBytes(Field field) {
// TODO: to verify each type of the field value conversion.
//        if(field.getType().getClass().getName().equals(String.class.getSimpleName().toLowerCase())){
        return null;
//            )
//        }
//        return new byte[0];
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
