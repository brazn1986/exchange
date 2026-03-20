package com.service.exchange.dto;

import java.io.Serializable;

public class ConvertCurrencyResponseDto implements Serializable {

    private String outAmount;
    private String inAmount;
    private String currencyIn;
    private String currencyOut;
    private String price;

    public String getOutAmount() {
        return outAmount;
    }

    public void setOutAmount(String outAmount) {
        this.outAmount = outAmount;
    }

    public String getCurrencyIn() {
        return currencyIn;
    }

    public void setCurrencyIn(String currencyIn) {
        this.currencyIn = currencyIn;
    }

    public String getCurrencyOut() {
        return currencyOut;
    }

    public void setCurrencyOut(String currencyOut) {
        this.currencyOut = currencyOut;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getInAmount() {
        return inAmount;
    }

    public void setInAmount(String inAmount) {
        this.inAmount = inAmount;
    }
}
