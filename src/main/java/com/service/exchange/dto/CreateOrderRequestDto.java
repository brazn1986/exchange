package com.service.exchange.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class CreateOrderRequestDto implements Serializable {

    private Integer userId;
    private String currencyIn;
    private String currencyOut;
    private BigDecimal amount;
    private BigDecimal price;

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
