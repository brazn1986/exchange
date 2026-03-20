package com.service.exchange.service.convertor;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component("RUBtoUSDT")
public class RubToUsdtConvertor implements ICurrencyConvertor {

    @Override
    public BigDecimal convert(BigDecimal amount, BigDecimal rate) {
        return amount.divide(rate, MATH_CONTEXT)
                .setScale(USDT_SCALE, RoundingMode.HALF_EVEN);
    }
}
