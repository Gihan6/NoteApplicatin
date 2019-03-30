
package com.creativematrix.noteapp.data.coins;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CurrencyList  implements Serializable {

    @Expose
    private String $id;
    @SerializedName("CurrencyID")
    private Long currencyID;
    @SerializedName("CurrencyName")
    private String currencyName;

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public Long getCurrencyID() {
        return currencyID;
    }

    public void setCurrencyID(Long currencyID) {
        this.currencyID = currencyID;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

}
