package org.gamecenter.serializer.utils;

import org.gamecenter.serializer.messages.Field;
import org.gamecenter.serializer.messages.Message;
import org.gamecenter.serializer.messages.MessageLoader;
import org.gamecenter.serializer.messages.upStream.LogonRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chevis on 14-7-16.
 */
public class Decoder<T> {

    private final static int MSG_TYPE_LENGTH = 2;
    private final static int MSG_SIZE_LENGTH = 2;
    private final static int MINIMUM_MSG_SIZE = MSG_TYPE_LENGTH + MSG_SIZE_LENGTH;

    MessageLoader factory = MessageLoader.INSTANCE();
    private Logger logger = LoggerFactory.getLogger(Decoder.class);
    private T msgClass = null;

    public static byte[] readBytes(InputStream in, long length) throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int read = 0;
        while (read < length) {
            int cur = in.read(buffer, 0, (int) Math.min(1024, length - read));
            if (cur < 0) {
                break;
            }
            read += cur;
            bo.write(buffer, 0, cur);
        }
        return bo.toByteArray();
    }

    public List<Field> decode(byte[] bytes) throws IOException {
        List<Field> fields=new ArrayList<Field>();
        int pointer = 0;

        if (bytes.length < MINIMUM_MSG_SIZE) {
            throw new InvalidParameterException("Message is too short to be decoded. Message size(" + bytes.length + ") is smaller than the minimum size(" + MINIMUM_MSG_SIZE + ").");
        }

        int msgId = ByteUtil.getInt(ByteUtil.subBytes(bytes, 0, MINIMUM_MSG_SIZE));
        logger.info("Message ID = {}", msgId);

        Message message = factory.getMessage(msgId);
        logger.info("Message(id={}) is retrieved: {}", msgId, message);

        try {
            String packagePath = Message.class.getPackage().getName() + "." + message.getDirection().toLowerCase() + "Stream.";
            msgClass = (T) Class.forName(packagePath + message.getName(), true, this.getClass().getClassLoader());
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage(), e);
        }

        pointer += MINIMUM_MSG_SIZE;
        List<Field> fieldList = message.getFields();
        for (Field field : fieldList) {
            byte[] fieldBytes = ByteUtil.subBytes(bytes, pointer, field.getLength());
            Field fieldVal;
            logger.debug("Read byte(s) value: {}", fieldBytes);
            if (byte.class.getSimpleName().toLowerCase().equals(field.getType().toLowerCase())) {
                byte tmpBytes = fieldBytes[0];
                fieldVal = new Field<Byte>(field);
                fieldVal.setValue(tmpBytes);
                System.err.println("Read value: " + fieldVal.getValue());
                fields.add(fieldVal);
            } else if ((byte.class.getSimpleName().toLowerCase()+"s").equals(field.getType().toLowerCase())) {
                fieldVal = new Field<Byte[]>(field);
                fieldVal.setValue(fieldBytes);
                System.err.println("Read Byte[] value: " + fieldVal.getValue());
                fields.add(fieldVal);
//                setValue(fieldVal,message);
            }else if(String.class.getSimpleName().toLowerCase().equals(field.getType().toLowerCase())){
                fieldVal = new Field<String>(field);
                fieldVal.setValue(ByteUtil.getString(fieldBytes));
                System.err.println("Read String value: " + fieldVal.getValue());
//                setValue(fieldVal,message);
                fields.add(fieldVal);
            }
                pointer += field.getLength();
        }

        return fields;
    }

//    private void setValue(Field field, Message message){
//        LogonRequest re = new LogonRequest();
//        String packagePath = Message.class.getPackage().getName() + "." + message.getDirection().toLowerCase() + "Stream.";
//        try {
//            java.lang.reflect.Field tempField = Class.forName(packagePath + message.getName()).getDeclaredField(field.getName());
//    tempField.setAccessible(true);
//            System.err.println(field.getValue()+", "+field.getType());
//
////            Object ob = (T)msgClass.getClass().getDeclaredField(field.getName());
//
//            tempField.set(Class.forName(packagePath + message.getName(),true,this.getClass().getClassLoader()), field.getValue());
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//
//        java.lang.reflect.Field[] fies = msgClass.getClass().getFields();
//        System.err.println("length"+fies.length);
//        for(java.lang.reflect.Field fie : fies){
//            System.err.println("fie"+fie.getName());}
//
//
//    }
}
