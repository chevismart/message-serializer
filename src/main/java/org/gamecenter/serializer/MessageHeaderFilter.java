package org.gamecenter.serializer;

/**
 * Created by Chevis on 14-7-24.
 */
public class MessageHeaderFilter implements HeaderFilter {
    @Override
    protected boolean verify() {
        return false;
    }
}
