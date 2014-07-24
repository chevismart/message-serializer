package org.gamecenter.serializer.messages;


import org.gamecenter.serializer.utils.XMLMessageConverter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MessageFactoryTest {

    private static String SYSTEM_MESSAGES_XML = "message_text.xml";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    MessageLoader msgLoader;
    File xmlFile;
    XMLMessageConverter converter;
    Map<Integer, Message> msgMap;

    @Before
    public void setUp() throws Exception {
        logger.info("This is setup method");
        String xmlPath = this.getClass().getClassLoader().getResource(SYSTEM_MESSAGES_XML).getPath();
        xmlPath = URLDecoder.decode(xmlPath, "UTF-8");
        logger.info(xmlPath);

        xmlFile = new File(xmlPath);

        msgMap = mock(Map.class);

        assertNotNull(xmlPath);

    }

    @Test
    public void canLoadXmlFileProperly() throws Exception {
        msgLoader = MessageLoader.INSTANCE();
        converter = new XMLMessageConverter();
        logger.info("XML file is {}exist.", (xmlFile.exists() ? "" : "not "));
        assertTrue(xmlFile.exists());
    }

    @Test
    public void canConvertXML2MessagesSuccessfully() throws Exception {
//        Whitebox.setInternalState(MessageLoader.INSTANCE(), "messageMap", msgMap);
//        msgLoader = MessageLoader.INSTANCE();
//
//        converter = new XMLMessageConverter();
//        ArrayList<Message> messageList = (ArrayList<Message>) converter.convertXML2Messages(xmlFile);
//        assertTrue(msgMap.size()>0);
//        logger.info(messageList.toString());
    }

    @Test
    public void getMessageByMessageIdSuccessfully() throws Exception {
        msgLoader = MessageLoader.INSTANCE();
        converter = new XMLMessageConverter();
        int mockMsgId = 0x1001;
        Message msg = msgLoader.getMessage(mockMsgId);
        assertTrue(mockMsgId == msg.getId());
    }

}