package org.gamecenter.serializer.messages.downStream;

import org.gamecenter.serializer.Encoder;
import org.junit.Test;

public class LoginResponseTest {

    @Test
    public void loadMessageDefinitionSuccessfully() throws Exception {

        Encoder encoder = new Encoder();

        LoginResponse response = new LoginResponse(encoder);

    }
}