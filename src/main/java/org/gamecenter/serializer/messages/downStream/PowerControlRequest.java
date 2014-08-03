package org.gamecenter.serializer.messages.downStream;

import org.gamecenter.serializer.messages.AbstractMessage;

/**
 * Created by Boss on 2014/7/30.
 */
public class PowerControlRequest extends AbstractMessage {
    private String switcher;

    public String getSwitcher() {
        return switcher;
    }

    public void setSwitcher(String switcher) {
        this.switcher = switcher;
    }

}
