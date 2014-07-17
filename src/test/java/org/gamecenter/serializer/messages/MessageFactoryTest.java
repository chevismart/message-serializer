package org.gamecenter.serializer.messages;


import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MessageFactoryTest {

    MessageFactory factory;
    private static String SYSTEM_MESSAGES_XML="SystemMessages.xml";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before
    public void setUp() throws Exception {
        factory = new MessageFactory();
        System.err.println("This is setup method");
    }

    @Test
    public void canLoadXmlFileProperly() throws Exception {

        String xmlPath = this.getClass().getClassLoader().getResource(SYSTEM_MESSAGES_XML).getPath();
        logger.info(xmlPath);
        assertNotNull(xmlPath);
        File xmlFile = new File(xmlPath);
        assertEquals(SYSTEM_MESSAGES_XML,xmlFile.getName());
        logger.info("XML file is {} exist.", (xmlFile.exists()?"":"not"));
        assertTrue(xmlFile.exists());
    }
}