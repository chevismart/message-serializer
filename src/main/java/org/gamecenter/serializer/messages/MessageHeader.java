package org.gamecenter.serializer.messages;

import org.gamecenter.serializer.constants.MessageType;

import java.util.Arrays;

/**
 * Created by Chevis on 14-7-26.
 */
public class MessageHeader {

    private byte[] messageSN;
    private byte[] deviceId;
    private byte[] messageId;
    private MessageType msgType;
    private int msgBodyLength;

    @Override
    public String toString() {
        return "MessageHeader{" +
                "messageSN=" + Arrays.toString(messageSN) +
                ", deviceId=" + Arrays.toString(deviceId) +
                ", messageId=" + Arrays.toString(messageId) +
                ", msgType=" + msgType +
                ", msgBodyLength=" + msgBodyLength +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageHeader header = (MessageHeader) o;

        if (msgBodyLength != header.msgBodyLength) return false;
        if (!Arrays.equals(deviceId, header.deviceId)) return false;
        if (!Arrays.equals(messageId, header.messageId)) return false;
        if (!Arrays.equals(messageSN, header.messageSN)) return false;
        if (msgType != header.msgType) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = messageSN != null ? Arrays.hashCode(messageSN) : 0;
        result = 31 * result + (deviceId != null ? Arrays.hashCode(deviceId) : 0);
        result = 31 * result + (messageId != null ? Arrays.hashCode(messageId) : 0);
        result = 31 * result + (msgType != null ? msgType.hashCode() : 0);
        result = 31 * result + msgBodyLength;
        return result;
    }

    public byte[] getMessageId() {
        return messageId;
    }

    public void setMessageId(byte[] messageId) {
        this.messageId = messageId;
    }

    public void setMsgType(MessageType msgType) {
        this.msgType = msgType;
    }

    public int getMsgBodyLength() {

        return msgBodyLength;
    }

    public MessageType getMsgType() {
        return msgType;
    }

    public void setMsgBodyLength(int msgBodyLength) {
        this.msgBodyLength = msgBodyLength;
    }

    public byte[] getMessageSN() {
        return messageSN;
    }

    public void setMessageSN(byte[] messageSN) {
        this.messageSN = messageSN;
    }

    public byte[] getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(byte[] deviceId) {
        this.deviceId = deviceId;
    }

}
