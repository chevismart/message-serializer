package org.gamecenter.serializer.messages;

import org.gamecenter.serializer.utils.XMLMessageConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chevis on 14-7-17.
 */
public class MessageFactory {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Map<Integer, Message> messageMap;
    XMLMessageConverter converter;
    private static String SYSTEM_MESSAGES_XML = "messages/SystemMessages.xml";
    private static MessageFactory factory;

    public static MessageFactory INSTANCE() {
        if (null == factory) {
            factory = new MessageFactory();
        }
        return factory;
    }

    public MessageFactory() {
        converter = new XMLMessageConverter();
        messageMap = new HashMap<Integer, Message>();
        String xmlPath = this.getClass().getClassLoader().getResource(SYSTEM_MESSAGES_XML).getPath();
        logger.info("XML path is {}", xmlPath);
        File xmlFile = new File(xmlPath);
        if (!xmlFile.exists()) {
            logger.error("{} file not Found", xmlFile.getName());
        }
        ArrayList<Message> messageList = (ArrayList<Message>) converter.convertXML2Messages(xmlFile);
        for (Message msg : messageList) {
            messageMap.put(msg.getId(), msg);
        }

    }

    public Message getMessage(int mockMsgId) {
        return messageMap.get(mockMsgId);
    }
}
