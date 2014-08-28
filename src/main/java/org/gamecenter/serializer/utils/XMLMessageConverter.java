package org.gamecenter.serializer.utils;

import com.thoughtworks.xstream.XStream;
import org.gamecenter.serializer.messages.Field;
import org.gamecenter.serializer.messages.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chevis on 14-7-19.
 */
public class XMLMessageConverter extends XStream {

    private final static String ROOT_NODE = "messages";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public XMLMessageConverter() {

        this.alias(ROOT_NODE, List.class);
        this.alias(Message.class.getSimpleName().toLowerCase(), Message.class);
        this.alias(Field.class.getSimpleName().toLowerCase(), Field.class);
    }

    public String convertObject2XML(Object obj) {
        String xmlStr = null;
        try {
            xmlStr = this.toXML(obj);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return xmlStr;
    }


    public List<Message> convertXML2Messages(InputStream xmlFile) {
        List<Message> msgList = new ArrayList<Message>();
        try {
            msgList = (List<Message>) this.fromXML(xmlFile);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return msgList;
    }
}
