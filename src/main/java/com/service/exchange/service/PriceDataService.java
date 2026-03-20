package com.service.exchange.service;

import com.service.exchange.model.CurrencyPair;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PriceDataService {

    private final Map<CurrencyPair, BigDecimal> newestPrices = new ConcurrentHashMap<>();

    public void updatePriceForCurrencyPair(CurrencyPair currencyPair, BigDecimal newPrice) {
        newestPrices.put(currencyPair, newPrice);
    }

    public BigDecimal getNewestPrice(CurrencyPair currencyPair) {
        return newestPrices.get(currencyPair);
    }
}
