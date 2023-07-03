package com.riyan.login;

import com.google.gson.annotations.SerializedName;

public class RootModelForex {
    @SerializedName("rates")
    private RatesModelForex ratesmodel;

    public RatesModelForex getRatesmodel() {
        return ratesmodel;
    }

    public void setRatesModel(RatesModelForex ratesModel) {
        this.ratesmodel = ratesModel;
    }
    public RootModelForex(){}
}