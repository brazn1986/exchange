package com.service.exchange.utils;

import com.service.exchange.model.CurrencyPair;

public final class CurrencyNameUtil {

    public static CurrencyPair getCurrencyPair(String currencyIn, String currencyOut) {
        return CurrencyPair.valueOf(currencyIn + "to" + currencyOut);
    }
}
