package com.service.exchange.dto;


import java.io.Serializable;
import java.math.BigDecimal;

public class AccountDto implements Serializable {

    private BigDecimal rubBalance;
    private BigDecimal usdtBalance;
    private String userId;

    public BigDecimal getRubBalance() {
        return rubBalance;
    }

    public void setRubBalance(BigDecimal rubBalance) {
        this.rubBalance = rubBalance;
    }

    public BigDecimal getUsdtBalance() {
        return usdtBalance;
    }

    public void setUsdtBalance(BigDecimal usdtBalance) {
        this.usdtBalance = usdtBalance;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
