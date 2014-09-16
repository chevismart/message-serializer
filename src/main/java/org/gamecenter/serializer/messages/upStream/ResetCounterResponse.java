package org.gamecenter.serializer.messages.upStream;

import org.gamecenter.serializer.messages.AbstractMessage;

/**
 * Created by Boss on 2014/9/16.
 */
public class ResetCounterResponse extends AbstractMessage {
    private String resetCoinResult;
    private String resetPrizeResult;

    public String getResetCoinResult() {
        return resetCoinResult;
    }

    public void setResetCoinResult(String resetCoinResult) {
        this.resetCoinResult = resetCoinResult;
    }

    public String getResetPrizeResult() {
        return resetPrizeResult;
    }

    public void setResetPrizeResult(String resetPrizeResult) {
        this.resetPrizeResult = resetPrizeResult;
    }

    public boolean isResetCoinSuccess() {
        return isSuccess(resetCoinResult);
    }

    public boolean isResetPrizeSuccess() {
        return isSuccess(resetPrizeResult);
    }
}
