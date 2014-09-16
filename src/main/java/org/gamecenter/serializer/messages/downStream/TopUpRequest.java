package org.gamecenter.serializer.messages.downStream;

import org.gamecenter.serializer.messages.AbstractMessage;

/**
 * Created by Boss on 2014/9/16.
 */
public class TopUpRequest extends AbstractMessage {

    private String referenceId;
    private int topUpQuantity;

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public int getTopUpQuantity() {
        return topUpQuantity;
    }

    public void setTopUpQuantity(int topUpQuantity) {
        this.topUpQuantity = topUpQuantity;
    }
}
