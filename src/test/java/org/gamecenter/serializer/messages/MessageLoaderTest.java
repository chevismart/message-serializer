package org.gamecenter.serializer.messages;


import org.gamecenter.serializer.utils.ByteUtil;
import org.gamecenter.serializer.utils.XMLMessageConverter;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class MessageLoaderTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    MessageLoader msgLoader;
    XMLMessageConverter converter;

    @Before
    public void setUp() throws Exception {
        logger.info("This is setup method");
    }

    @Test
    public void canLoadXmlFileProperly() throws Exception {


        msgLoader = MessageLoader.INSTANCE();
//        converter = new XMLMessageConverter();
//        logger.info("XML file is {}exist.", (xmlFile.exists() ? "" : "not "));
//        assertTrue(xmlFile.exists());
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
        String mockMsgId = "0x110a";
        Message msg = msgLoader.getMessageByMsgId(mockMsgId);
        assertTrue(mockMsgId.equalsIgnoreCase(msg.getId()));
    }

    @Test
    public void getMessageByMessageNameSuccessfully() throws Exception {
        msgLoader = MessageLoader.INSTANCE();
        converter = new XMLMessageConverter();
        String msgName = "LoginRequest";
        Message msg = msgLoader.getMessageByName(msgName);
        String mockMsgId = "0x110A";
        assertTrue(mockMsgId.equalsIgnoreCase(msg.getId()));

    }
}