package com.service.exchange.service;

import com.service.exchange.dao.ConversionRequestHistoryRepository;
import com.service.exchange.entity.ConversionRequestHistory;
import com.service.exchange.model.ConvertCurrencyInfo;
import com.service.exchange.model.CurrencyPair;
import com.service.exchange.service.convertor.ICurrencyConvertor;
import com.service.exchange.utils.CurrencyUtil;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class ConvertCurrencyService {

    private PriceDataService priceDataService;

    private Map<String, ICurrencyConvertor> currencyConvertors;

    private ConversionRequestHistoryRepository conversionRequestHistoryRepository;

    private ValidationService validationService;

    public ConvertCurrencyService(PriceDataService priceDataService,
                                  ConversionRequestHistoryRepository conversionRequestHistoryRepository,
                                  Map<String, ICurrencyConvertor> currencyConvertors,
                                  ValidationService validationService) {
        this.priceDataService = priceDataService;
        this.currencyConvertors = currencyConvertors;
        this.conversionRequestHistoryRepository = conversionRequestHistoryRepository;
        this.validationService = validationService;
    }

    public ConvertCurrencyInfo convert(Integer userId, CurrencyPair currencyPair, BigDecimal amount) {

        amount = CurrencyUtil.prepareRounded(amount);
        validationService.getCurrencyConvertorValidator().validate(amount);

        BigDecimal price = priceDataService.getNewestPrice(currencyPair);
        BigDecimal outAmount = currencyConvertors.get(currencyPair.name()).convert(amount, price);

        ConvertCurrencyInfo currencyInfo = new ConvertCurrencyInfo();
        currencyInfo.setPrice(price);
        currencyInfo.setOutAmount(outAmount);
        currencyInfo.setInAmount(amount);

        ConversionRequestHistory conversionRequest = new ConversionRequestHistory();
        conversionRequest.setPrice(price);
        conversionRequest.setUserId(userId.toString());
        conversionRequestHistoryRepository.save(conversionRequest);

        return currencyInfo;
    }
}
