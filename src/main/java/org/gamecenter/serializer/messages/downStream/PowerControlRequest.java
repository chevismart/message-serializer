package org.gamecenter.serializer.messages.downStream;

import org.gamecenter.serializer.Encoder;
import org.gamecenter.serializer.messages.AbstractMessage;

/**
 * Created by Boss on 2014/7/30.
 */
public class PowerControlRequest extends AbstractMessage {
    private final Encoder encoder;
    private String switcher;

    public PowerControlRequest(Encoder encoder) {
        this.encoder = encoder;
    }

    public String getSwitcher() {
        return switcher;
    }

    public void setSwitcher(String switcher) {
        this.switcher = switcher;
    }

    @Override
    public byte[] build() {
        return new byte[0];
    }
}
