package org.gamecenter.serializer.messages;


import org.gamecenter.serializer.utils.XMLMessageConverter;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class MessageFactoryTest {

    private static String SYSTEM_MESSAGES_XML = "message_text.xml";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    MessageFactory factory;
    File xmlFile;
    XMLMessageConverter converter;
    Map<Integer, Message> msgMap;

    @Before
    public void setUp() throws Exception {
        logger.info("This is setup method");
        String xmlPath = this.getClass().getClassLoader().getResource(SYSTEM_MESSAGES_XML).getPath();
        logger.info(xmlPath);

        xmlFile = new File(xmlPath);

        msgMap = mock(Map.class);
        factory = MessageFactory.INSTANCE();
        assertNotNull(xmlPath);
        converter = new XMLMessageConverter();
    }

    @Test
    public void canLoadXmlFileProperly() throws Exception {
        logger.info("XML file is {}exist.", (xmlFile.exists() ? "" : "not "));
        assertTrue(xmlFile.exists());
    }

    @Test
    public void canConvertXML2MessagesSuccessfully() throws Exception {
        ArrayList<Message> messageList = (ArrayList<Message>) converter.convertXML2Messages(xmlFile);
        logger.info(messageList.toString());
    }

    @Test
    public void getMessageByMessageIdSuccessfully() throws Exception {
        int mockMsgId = 0x1000;
        Message msg = factory.getMessage(mockMsgId);
        assertTrue(mockMsgId == msg.getId());
    }

}