
package com.creativematrix.noteapp.data.coins;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DisplayCurrencyRequest {

    private  String CurrencyID;

    public DisplayCurrencyRequest(String currencyID) {
        CurrencyID = currencyID;
    }

    public String getCurrencyID() {
        return CurrencyID;
    }

    public void setCurrencyID(String currencyID) {
        CurrencyID = currencyID;
    }
}
