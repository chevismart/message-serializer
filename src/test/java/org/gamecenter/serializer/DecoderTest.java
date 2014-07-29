package org.gamecenter.serializer;

import org.gamecenter.serializer.messages.Field;
import org.gamecenter.serializer.messages.Message;
import org.gamecenter.serializer.messages.MessageLoader;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;
import org.slf4j.Logger;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DecoderTest {

    Logger logger = mock(Logger.class);
    Decoder decoder;

    @Before
    public void setUp() throws Exception {
        decoder = new Decoder();
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void throwArrayIndexOutOfBoundsExceptionIfBytesMsgIsShorterThanTheMinimumSize() throws Exception {
        byte[] bytes = new byte[]{0x2A, 0x01, 0x10, 0x04, 0x00, 0x06, 0x02, 0x06, 0x29, 0x01, 0x10, 0x04, 0x22, 0x23};
        decoder.decode(bytes);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void throwArrayIndexOutOfBoundsExceptionIfTheExactMsgLengthIsShorterThanTheLengthDefinedInTheMsgHeader() throws Exception {
        byte[] bytes = new byte[]{0x2A, 0x01, 0x10, 0x04, 0x00, 0x06, 0x02, 0x06, 0x29, 0x01, 0x10, 0x04, 0x00, 0x06, 0x02, 0x23};
        decoder.decode(bytes);
    }

    @Test
    public void convertBytesMessageIsEqualWithMinimumSizeSuccessfully() throws Exception {

        MessageLoader loader = mock(MessageLoader.class);
        Whitebox.setInternalState(decoder, "loader", loader);
        Message msg = new Message();
        msg.setName("LoginRequest");
        msg.setFields(new ArrayList<Field>());
        when(loader.getMessageByMsgId(anyInt())).thenReturn(msg);
        byte[] equalCase = new byte[]{0x2A, 0x01, 0x10, 0x04, 0x00, 0x06, 0x02, 0x06, 0x29, 0x01, 0x10, 0x00, 0x00, 0x23};
        assertNotNull(decoder.decode(equalCase));
    }

    @Test(expected = InvalidParameterException.class)
    public void messageIdNotFoundInTheMessageSpec() throws Exception {
        Message msg = new Message();
        msg.setName("LoginRequest");
        msg.setFields(new ArrayList<Field>());
        byte[] equalCase = new byte[]{0x2A, 0x01, 0x10, 0x04, 0x00, 0x06, 0x02, 0x06, 0x29, 0x01, 0x21, 0x00, 0x00, 0x23};
        assertNotNull(decoder.decode(equalCase));

    }

    @Test
    public void convertBytesMessageIsLongerThanMinimumSizeSuccessfully() throws Exception {
//        byte[] longerCase = new byte[]{0x01, 0x10, 0x03, 0x00, 0x06, 0x02, 0x06};
//        assertNotNull(decoder.decode(longerCase));
    }

    @Test
    public void convertBytesMessageAndCheckAllFieldIsMandatorySuccessfully() throws Exception {

    }

    @Test
    public void convertBytesMessageFailAndThrowExceptonSinceThereIsMandatoryFieldMissed() throws Exception {

    }


    @Test
    public void convertBytesMessageAndCheckAllNoneEmptyFieldSuccessfully() throws Exception {

    }


    @Test
    public void convertBytesMessageFailAndThrowExceptonSinceTheRequiringFieldIsEmpty() throws Exception {

    }

    @Test
    public void throwExceptionThatIfTheFieldQuantityAreNotTheSameWithTheMessageSpeacDefinition() throws Exception {

    }

    @Test
    public void testDecoderCanDecodeTheBytesMsgSuccessfully() throws Exception {


    }

    @Test
    public void testDecoderFailsDecodeBytesMsg() throws Exception {


    }
}