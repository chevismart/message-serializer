package org.gamecenter.serializer.utils;

import org.gamecenter.serializer.Decoder;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;

import java.security.InvalidParameterException;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class DecoderTest {

    Logger logger = mock(Logger.class);
    Decoder decoder;

    @Before
    public void setUp() throws Exception {
        decoder = new Decoder();
    }

    @Test(expected = InvalidParameterException.class)
    public void throwInvalidParameterExceptionIfBytesMsgIsShorterThanTheMinimumSize() throws Exception {
        byte[] bytes = new byte[]{0x01, 0x10, 0x04};
        decoder.decode(bytes);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void throwArrayIndexOutOfBoundsExceptionIfTheExactMsgLengthIsShorterThanTheLengthDefinedInTheMsgHeader() throws Exception {
        byte[] bytes = new byte[]{0x01, 0x10, 0x04, 0x00, 0x06, 0x02, 0x06};
        decoder.decode(bytes);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void throwIndexOutOfBoundsExceptionIfTheExactMsgLengthIsLongerThanTheLengthDefinedInTheMsgHeader() throws Exception {
        byte[] bytes = new byte[]{0x01, 0x10, 0x04, 0x00, 0x06, 0x02, 0x06, 0x02, 0x06, 0x06};
        decoder.decode(bytes);
    }

    @Test
    public void convertBytesMessageIsEqualWithMinimumSizeSuccessfully() throws Exception {
        byte[] equalCase =new byte[]{0x01, 0x10, 0x00, 0x00};
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