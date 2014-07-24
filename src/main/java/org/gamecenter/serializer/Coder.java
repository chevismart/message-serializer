package org.gamecenter.serializer;

/**
 * Created by Chevis on 14-7-24.
 */
public abstract class Coder {
    protected final static int MSG_TYPE_LENGTH = 2;
    protected final static int MSG_SIZE_LENGTH = 2;
    protected final static int MINIMUM_MSG_SIZE = MSG_TYPE_LENGTH + MSG_SIZE_LENGTH;

}
