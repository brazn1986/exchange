package com.service.exchange.service.planning;

import com.service.exchange.model.CurrencyPair;
import com.service.exchange.service.PriceDataService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

@Component
public class PriceGeneratorScheduler {

    private final Random priceRandom = new Random();
    private final BigDecimal min = new BigDecimal("80.111");
    private final BigDecimal max = new BigDecimal("95.744");

    private PriceDataService priceDataService;

    public PriceGeneratorScheduler(PriceDataService priceDataService) {
        this.priceDataService = priceDataService;
    }

    @Scheduled(fixedRate = 1000)
    public void generatePrice() {

        BigDecimal range = max.subtract(min);
        BigDecimal randomValue = BigDecimal.valueOf(priceRandom.nextDouble());
        BigDecimal newPrice = min.add(range.multiply(randomValue));

        newPrice = newPrice.setScale(3, RoundingMode.HALF_UP);

        priceDataService.updatePriceForCurrencyPair(CurrencyPair.RUBtoUSDT, newPrice);
        priceDataService.updatePriceForCurrencyPair(CurrencyPair.USDTtoRUB, newPrice);

    }


}
