package com.service.exchange.service.convertor;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component("USDTtoRUB")
public class UsdtToRubConvertor implements ICurrencyConvertor {

    @Override
    public BigDecimal convert(BigDecimal amount, BigDecimal rate) {
        return amount.multiply(rate, MATH_CONTEXT)
                .setScale(RUB_SCALE, RoundingMode.HALF_EVEN);
    }
}
