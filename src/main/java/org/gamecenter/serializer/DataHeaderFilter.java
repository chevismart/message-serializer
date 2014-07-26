package org.gamecenter.serializer;

/**
 * Created by Boss on 2014/7/26.
 */
public class DataHeaderFilter implements HeaderFilter{
    @Override
    protected boolean verify() {
        return false;
    }
}
