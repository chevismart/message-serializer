package org.gamecenter.serializer.utils;

import org.gamecenter.serializer.messages.MessageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.InvalidParameterException;

/**
 * Created by Chevis on 14-7-16.
 */
public class Decoder<T> {

    private final static int MSG_TYPE_LENGTH = 2;
    private final static int MSG_SIZE_LENGTH = 2;
    private final static int MINIMUM_MSG_SIZE = MSG_TYPE_LENGTH + MSG_SIZE_LENGTH;
    MessageFactory factory = MessageFactory.INSTANCE();
    private Logger logger = LoggerFactory.getLogger(Decoder.class);

    public T decode(byte[] bytes) {

        if (bytes.length < MINIMUM_MSG_SIZE) {
            throw new InvalidParameterException("Message is too short to be decoded. Size(" + bytes.length + ") is smaller than the minimum size(" + MINIMUM_MSG_SIZE + ").");
        }

        return null;
    }
}
