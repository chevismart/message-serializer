package org.gamecenter.serializer.messages.upStream;

import org.gamecenter.serializer.messages.AbstractMessage;

/**
 * Created by Boss on 2014/9/16.
 */
public class TopUpResponse extends AbstractMessage {
    private String referenceId;
    private String topUpResult;

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getTopUpResult() {
        return topUpResult;
    }

    public void setTopUpResult(String topUpResult) {
        this.topUpResult = topUpResult;
    }
}
