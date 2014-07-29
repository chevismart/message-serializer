package org.gamecenter.serializer.messages;

import org.gamecenter.serializer.utils.XMLMessageConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chevis on 14-7-17.
 */
public class MessageLoader {

    private static String SYSTEM_MESSAGES_XML = "messages/systemMessages.xml";
    private static MessageLoader factory;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    XMLMessageConverter converter;
    private Map<Integer, Message> messageMap;

    public MessageLoader() {
        converter = new XMLMessageConverter();
        messageMap = new HashMap<Integer, Message>();
        String xmlPath = this.getClass().getClassLoader().getResource(SYSTEM_MESSAGES_XML).getPath();
        try {
            xmlPath = URLDecoder.decode(xmlPath, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        logger.info("XML path is {}", xmlPath);
        File xmlFile = new File(xmlPath);
        if (!xmlFile.exists()) {
            logger.error("{} file not Found", xmlFile.getName());
        }
        ArrayList<Message> messageList = (ArrayList<Message>) converter.convertXML2Messages(xmlFile);
        for (Message msg : messageList) {
            int minConunter = 0;
            int maxConunter = 0;
            for (Field field : msg.getFields()) {
                int fieldLength = field.getLength();
                if (field.isMandatory()) {
                    minConunter += fieldLength;
                }
                maxConunter += fieldLength;
            }
            msg.setMinLength(minConunter);
            msg.setMaxLength(maxConunter);
            messageMap.put(msg.getId(), msg);
        }

    }

    public static MessageLoader INSTANCE() {
        if (null == factory) {
            factory = new MessageLoader();
        }
        return factory;
    }

    public Message getMessageByMsgId(int mockMsgId) {
        return messageMap.get(mockMsgId);
    }

    public Message getMessageByName(String msgName) {

        for (Message msg : messageMap.values()) {
            if (msg.getName().toLowerCase().equals(msgName.toLowerCase())) {
                return msg;
            }
        }
        return null;
    }
}
