package com.service.exchange.service.convertor;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public interface ICurrencyConvertor {

    int CALCULATION_PRECISION = 10;
    int USDT_SCALE = 3;
    int RUB_SCALE = 0;
    MathContext MATH_CONTEXT = new MathContext(CALCULATION_PRECISION, RoundingMode.HALF_EVEN);

    BigDecimal convert(BigDecimal amount, BigDecimal price);
}
