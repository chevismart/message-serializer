package org.gamecenter.serializer.messages.upStream;

import org.gamecenter.serializer.messages.AbstractMessage;
import org.gamecenter.serializer.utils.ByteUtil;

import java.util.Arrays;

/**
 * Created by Boss on 2014/9/5.
 */
public class ConnectionResponse extends AbstractMessage {
    private byte wifiSignal;
    private byte[] ipAdd;
    private byte[] mac;

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                this.getHeader().toString() + "," +
                "wifiSignal=" + wifiSignal +
                ", ipAdd=" + Arrays.toString(ipAdd) +
                ", mac=" + Arrays.toString(mac) +
                '}';
    }

    public byte getWifiSignal() {
        return wifiSignal;
    }

    public void setWifiSignal(byte wifiSignal) {
        this.wifiSignal = wifiSignal;
    }

    public byte[] getIpAdd() {
        return ipAdd;
    }

    public void setIpAdd(byte[] ipAdd) {
        this.ipAdd = ipAdd;
    }

    public byte[] getMac() {
        return mac;
    }

    public void setMac(byte[] mac) {
        this.mac = mac;
    }
}
