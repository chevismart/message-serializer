package org.gamecenter.serializer.messages;


import org.gamecenter.serializer.utils.XMLMessageConverter;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class MessageFactoryTest {

    private static String SYSTEM_MESSAGES_XML = "message/SystemMessages.xml";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    MessageFactory factory;
    File xmlFile;
    XMLMessageConverter converter;

    @Before
    public void setUp() throws Exception {
        logger.info("This is setup method");
        String xmlPath = this.getClass().getClassLoader().getResource(SYSTEM_MESSAGES_XML).getPath();
        factory = new MessageFactory();
        logger.info(xmlPath);
        assertNotNull(xmlPath);
        xmlFile = new File(xmlPath);
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


}