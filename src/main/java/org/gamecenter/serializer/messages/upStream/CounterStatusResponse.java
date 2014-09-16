package org.gamecenter.serializer.messages.upStream;

import org.gamecenter.serializer.messages.AbstractMessage;

/**
 * Created by Boss on 2014/9/16.
 */
public class CounterStatusResponse extends AbstractMessage {
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
