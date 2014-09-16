package org.gamecenter.serializer.messages.downStream;

import org.gamecenter.serializer.messages.AbstractMessage;

/**
 * Created by Boss on 2014/9/16.
 */
public class ResetCounterRequest extends AbstractMessage {
    private String resetCoin;
    private String resetPrize;

    public String getResetCoin() {
        return resetCoin;
    }

    public void setResetCoin(boolean resetCoin) {
        this.resetCoin = isEnabled(resetCoin);
    }

    public void setResetCoin(String resetCoin) {
        this.resetCoin = resetCoin;
    }

    public String getResetPrize() {
        return resetPrize;
    }

    public void setResetPrize(boolean resetPrize) {
        this.resetPrize = isEnabled(resetPrize);
    }

    public void setResetPrize(String resetPrize) {
        this.resetPrize = resetPrize;
    }
}
