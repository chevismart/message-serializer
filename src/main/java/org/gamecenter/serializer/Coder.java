package org.gamecenter.serializer;

/**
 * Created by Chevis on 14-7-24.
 */
public abstract class Coder {
    protected final static int MSG_TYPE_LENGTH = 2;
    protected final static int MSG_SIZE_LENGTH = 2;
    protected final static int MINIMUM_MSG_SIZE = MSG_TYPE_LENGTH + MSG_SIZE_LENGTH;
    protected final static int START_FLAG_LENGTH = 1;
    protected final static int MSG_SEQUENCE_LENGTH = 4;
    protected final static int DEVICE_ID_LENGTH = 4;
    protected final static int MESSAGE_TYPE_LENGTH = 2;
    protected final static int DATA_LENGTH = 2;
    public final static int TOTAL_HEADER_LENGTH = START_FLAG_LENGTH + MSG_SEQUENCE_LENGTH + DEVICE_ID_LENGTH + MESSAGE_TYPE_LENGTH + DATA_LENGTH;
    protected final static int END_FLAG_LENGTH = 1;

    protected final static byte START_FLAG = 0x2A;
    protected final static byte END_FLAG = 0x23;
}
