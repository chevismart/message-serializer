package org.gamecenter.serializer.messages;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessageFactoryTest {

    MessageFactory factory;

    @Before
    public void setUp() throws Exception {
        factory = new MessageFactory();
        System.err.println("This is setup method");
    }

    @Test
    public void testName() throws Exception {
        System.err.println("This is a test case");


    }
}