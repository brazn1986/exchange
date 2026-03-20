package com.service.exchange.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class CurrencyUtil {

    public static BigDecimal prepareRounded(BigDecimal amount) {
        return new BigDecimal(amount.setScale(-2, RoundingMode.HALF_UP).toPlainString());
    }
}
