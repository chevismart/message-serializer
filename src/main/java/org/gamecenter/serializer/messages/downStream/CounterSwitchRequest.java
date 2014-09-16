package org.gamecenter.serializer.messages.downStream;

import org.gamecenter.serializer.messages.AbstractMessage;

/**
 * Created by Boss on 2014/9/16.
 */
public class CounterSwitchRequest extends AbstractMessage {
    private String switcher;

    public String getSwitcher() {
        return switcher;
    }

    public void setSwitcher(String switcher) {
        this.switcher = switcher;
    }
}
