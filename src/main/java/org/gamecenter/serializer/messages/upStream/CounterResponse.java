package org.gamecenter.serializer.messages.upStream;

import org.gamecenter.serializer.messages.AbstractMessage;

/**
 * Created by Boss on 2014/9/16.
 */
public class CounterResponse extends AbstractMessage {
    private int coinQuantity;
    private int prizeQuantity;

    public int getCoinQuantity() {
        return coinQuantity;
    }

    public void setCoinQuantity(int coinQuantity) {
        this.coinQuantity = coinQuantity;
    }

    public int getPrizeQuantity() {
        return prizeQuantity;
    }

    public void setPrizeQuantity(int prizeQuantity) {
        this.prizeQuantity = prizeQuantity;
    }
}
