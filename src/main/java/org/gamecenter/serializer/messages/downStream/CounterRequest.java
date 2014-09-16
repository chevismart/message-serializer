package org.gamecenter.serializer.messages.downStream;

import org.gamecenter.serializer.messages.AbstractMessage;

/**
 * Created by Boss on 2014/9/16.
 */
public class CounterRequest extends AbstractMessage {
    String reqCoin;
    String reqPrize;

    public String getReqCoin() {
        return reqCoin;
    }

    public void setReqCoin(boolean reqCoin) {
        this.reqCoin = isEnabled(reqCoin);
    }

    public void setReqCoin(String reqCoin) {
        this.reqCoin = reqCoin;
    }

    public String getReqPrize() {
        return reqPrize;
    }

    public void setReqPrize(boolean reqPrize) {
        this.reqPrize = isEnabled(reqPrize);
    }

    public void setReqPrize(String reqPrize) {
        this.reqPrize = reqPrize;
    }

}
