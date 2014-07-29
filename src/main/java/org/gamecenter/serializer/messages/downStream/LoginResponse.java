package org.gamecenter.serializer.messages.downStream;

import org.gamecenter.serializer.Encoder;
import org.gamecenter.serializer.messages.AbstractMessage;


/**
 * Created by Chevis on 14-7-29.
 */
public class LoginResponse extends AbstractMessage {

    private final Encoder encoder;

    public LoginResponse(Encoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public byte[] build() {
        return encoder.encode(this);
    }
}
