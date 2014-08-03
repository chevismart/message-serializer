package org.gamecenter.serializer.messages;

import org.apache.commons.lang3.StringUtils;
import org.gamecenter.serializer.constants.MessageConstants;
import org.gamecenter.serializer.utils.XMLMessageConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

/**
 * Created by Chevis on 14-7-17.
 */
public class MessageLoader {

    private static MessageLoader factory;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    XMLMessageConverter converter;
    private Map<Short, Message> messageMap;

    public MessageLoader() {
        converter = new XMLMessageConverter();
        messageMap = new HashMap<Short, Message>();
        Properties config = getConfig();
        List<String> msgList = Arrays.asList(((String) config.get(MessageConstants.ALL_MSG_SPEC_KEY)).split(","));
        for (String msgSpec : msgList) {
            String path = (String) config.get(msgSpec);
            messageMap.putAll(convertToMessageMap(path));
        }
    }

    public static MessageLoader INSTANCE() {
        if (null == factory) {
            factory = new MessageLoader();
        }
        return factory;
    }

    private Map<Short, Message> convertToMessageMap(String msgSpec) {
        Map<Short, Message> msgMap = new HashMap<Short, Message>();

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

        File msgXML = null;
        try {
            URL url = this.getClass().getClassLoader().getResource(msgSpecPath);
            msgXML = new File(url.toURI());
        } catch (URISyntaxException e) {
            logger.error("XML file is not exists.");
        }
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

    public Message getMessageByMsgId(short mockMsgId) {
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
