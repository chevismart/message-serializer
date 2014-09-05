package org.gamecenter.serializer.messages.upStream;

import org.gamecenter.serializer.messages.AbstractMessage;

/**
 * Created by Boss on 2014/9/4.
 */
public class HeartbeatRequest extends AbstractMessage {
    @Override
    public String toString(){
        return this.getClass().getSimpleName() + ":" +
                this.getHeader().toString();
    }
}
