package org.gamecenter.serializer.messages.upStream;

import org.gamecenter.serializer.messages.AbstractMessage;

/**
 * Created by Boss on 2014/9/16.
 */
public class RuntimeResponse extends AbstractMessage {
    private short runtime;

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(short runtime) {
        this.runtime = runtime;
    }

}
