package org.gamecenter.serializer.messages;

import org.apache.commons.lang3.StringUtils;
import org.gamecenter.serializer.constants.MessageConstants;
import org.gamecenter.serializer.utils.XMLMessageConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

/**
 * Created by Chevis on 14-7-17.
 */
public class MessageLoader {

    private static String SYSTEM_MESSAGES_XML = "messages/systemMessages.xml";
    private static String ENCODE_TYPE = "UTF-8";
    private static MessageLoader factory;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    XMLMessageConverter converter;
    private Map<Integer, Message> messageMap;

    public MessageLoader() {

        messageMap = new HashMap<Integer, Message>();

        Properties config = getConfig();

        List<String> msgList = Arrays.asList(((String) config.get(MessageConstants.ALL_MSG_SPEC_KEY)).split(","));

        for (String msgSpec : msgList) {
            String path = (String) config.get(msgSpec);
            messageMap.putAll(convertToMessageMap(path));
        }

//
//        converter = new XMLMessageConverter();
//        messageMap = new HashMap<Integer, Message>();
//        String xmlPath = this.getClass().getClassLoader().getResource(SYSTEM_MESSAGES_XML).getPath();
//        try {
//            xmlPath = URLDecoder.decode(xmlPath, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        logger.info("XML path is {}", xmlPath);
//        File xmlFile = new File(xmlPath);
//        if (!xmlFile.exists()) {
//            logger.error("{} file not Found", xmlFile.getName());
//        }
//        ArrayList<Message> messageList = (ArrayList<Message>) converter.convertXML2Messages(xmlFile);
//        for (Message msg : messageList) {
//            int minConunter = 0;
//            int maxConunter = 0;
//            for (Field field : msg.getFields()) {
//                int fieldLength = field.getLength();
//                if (field.isMandatory()) {
//                    minConunter += fieldLength;
//                }
//                maxConunter += fieldLength;
//            }
//            msg.setMinLength(minConunter);
//            msg.setMaxLength(maxConunter);
//            messageMap.put(msg.getId(), msg);
//        }

    }

    public static MessageLoader INSTANCE() {
        if (null == factory) {
            factory = new MessageLoader();
        }
        return factory;
    }

    private Map<Integer, Message> convertToMessageMap(String msgSpec) {
        Map<Integer, Message> msgMap = new HashMap<Integer, Message>();

        File xmlFile = loadMessageSpec(msgSpec);

        if (xmlFile.exists()) {

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
                msgMap.put(msg.getId(), msg);
            }

            logger.info("Message Spec XML({}) is converted.", msgSpec);

        } else {
            logger.error("Message Spec XML file is not exists. No message is converted.");
        }


        return msgMap;
    }

    private File loadMessageSpec(String msgSpecPath) {

        if (StringUtils.isEmpty(msgSpecPath)) {
            logger.warn("XML path is empty, there is no message spec to be load.");
            return null;
        }

        String url = null;

        try {
            String tmp = URLDecoder.decode(msgSpecPath, ENCODE_TYPE);
            String path = Object.class.getClass().getResource("").getPath();
            url = this.getClass().getClassLoader().getResource(tmp).getPath();
        } catch (UnsupportedEncodingException e) {
            logger.error("Load the message spec {} failed!", msgSpecPath);
        }
        File msgXML = new File(url);
        logger.info("XML({}) is loaded.", url);

        return msgXML;
    }

    private Properties getConfig() {
        Properties config = new Properties();
        try {
            config.load(this.getClass().getClassLoader().getResourceAsStream(MessageConstants.CONFIG_FILE_NAME));
        } catch (IOException e) {
            logger.error("Config Properties {} not found!", MessageConstants.CONFIG_FILE_NAME);
        }
        return config;
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
