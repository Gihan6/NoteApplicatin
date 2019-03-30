
package com.creativematrix.noteapp.data.coins;

import java.util.List;

import com.google.gson.annotations.Expose;


@SuppressWarnings("unused")
public class DisplayCurrencyResponse {

    @Expose
    private String $id;
    @Expose
    private List<CurrencyList> currencyList;
    @Expose
    private String flag;

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public List<CurrencyList> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<CurrencyList> currencyList) {
        this.currencyList = currencyList;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

}
