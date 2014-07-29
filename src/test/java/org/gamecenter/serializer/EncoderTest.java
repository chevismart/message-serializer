package org.gamecenter.serializer;

import org.gamecenter.serializer.messages.AbstractMessage;
import org.gamecenter.serializer.messages.downStream.LoginResponse;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class EncoderTest {

    Encoder encoder;

    @Test
    public void encodeAnEmptyFieldMessageSuccessfully() throws Exception {

        encoder = new Encoder();
        AbstractMessage mockMsg = mock(AbstractMessage.class);

        encoder.encode(new LoginResponse(encoder));

    }
}