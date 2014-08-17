package org.gamecenter.serializer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chevis on 14-7-24.
 */
public abstract class Codec {
    protected final static int MSG_TYPE_LENGTH = 2;
    protected final static int MSG_SIZE_LENGTH = 2;
    protected final static int MINIMUM_MSG_SIZE = MSG_TYPE_LENGTH + MSG_SIZE_LENGTH;
    protected final static int START_FLAG_LENGTH = 1;
    protected final static int MSG_SEQUENCE_LENGTH = 4;
    protected final static int DEVICE_ID_LENGTH = 4;
    protected final static int MESSAGE_TYPE_LENGTH = 2;
    protected final static int DATA_LENGTH = 2;
    public final static int TOTAL_HEADER_LENGTH = START_FLAG_LENGTH + MSG_SEQUENCE_LENGTH + DEVICE_ID_LENGTH + MESSAGE_TYPE_LENGTH + DATA_LENGTH;
    protected final static int CRC_LENGTH = 2;
    protected final static int END_FLAG_LENGTH = 1;
    public final static int TAILER_LENGTH = CRC_LENGTH + END_FLAG_LENGTH;

    protected final static byte START_FLAG = 0x2A;
    protected final static byte END_FLAG = 0x23;

    protected byte[] getTailer() {
        // TODO: Refactor for the tailer later
        List<Byte> tailerList = new ArrayList<Byte>();

        tailerList.add((byte) 0x00);
        tailerList.add((byte) 0x00);
        tailerList.add(END_FLAG);

        byte[] tailer = new byte[tailerList.size()];

        for (int i = 0; i < tailerList.size(); i++) {
            tailer[i] = tailerList.get(i);
        }
        return tailer;

    }

}
