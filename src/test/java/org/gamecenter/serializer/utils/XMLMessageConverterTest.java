package org.gamecenter.serializer.utils;

import org.gamecenter.serializer.messages.Field;
import org.gamecenter.serializer.messages.Message;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class XMLMessageConverterTest {

    private final XMLMessageConverter converter = new XMLMessageConverter();
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testConvertObject2XML() throws Exception {
        Message message = new Message();
        message.setName("field");
        message.setDescription("desc");
        ArrayList list = new ArrayList();
        Field field = new Field();
        field.setValue("ddd");
        field.setLength(1);
        list.add(field);
        message.setFields(list);
        ArrayList<Message> msgList = new ArrayList<Message>();
        msgList.add(message);

        String xml = converter.convertObject2XML(msgList);
        logger.info(xml);
        assertTrue(xml.length() > 0);
        assertTrue(xml.contains("field"));
        assertTrue(xml.contains("ddd"));
    }

    @Test
    public void testConvertXML2Object() throws Exception {

        InputStream is = this.getClass().getClassLoader().getResourceAsStream("message_text.xml");
        assertNotNull(is);
        logger.info("PowerMessages.xml is loaded.");
        ArrayList<Message> messageList = (ArrayList<Message>) converter.convertXML2Messages(is);
        logger.info(messageList.toString());
        assertTrue(messageList.size() > 0);

    }
}