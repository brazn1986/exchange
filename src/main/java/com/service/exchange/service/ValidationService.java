package com.service.exchange.service;

import com.service.exchange.dao.OrderRepository;
import com.service.exchange.service.convertor.ICurrencyConvertor;
import com.service.exchange.service.validator.*;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ValidationService {

    private PriceValidator priceValidator;
    private OrderValidator createOrderValidator;
    private AccountValidator accountValidator;
    private CurrencyNameValidator currencyNameValidator;
    private CurrencyConvertorValidator currencyConvertorValidator;

    public ValidationService(PriceValidator priceValidator,
                             OrderValidator createOrderValidator,
                             AccountValidator accountValidator,
                             CurrencyNameValidator currencyNameValidator,
                             CurrencyConvertorValidator currencyConvertorValidator) {

        this.priceValidator = priceValidator;
        this.createOrderValidator = createOrderValidator;
        this.accountValidator = accountValidator;
        this.currencyNameValidator = currencyNameValidator;
        this.currencyConvertorValidator = currencyConvertorValidator;
    }

    public PriceValidator getPriceValidator() {
        return priceValidator;
    }

    public OrderValidator getCreateOrderValidator() {
        return createOrderValidator;
    }

    public AccountValidator getAccountValidator() {
        return accountValidator;
    }

    public CurrencyNameValidator getCurrencyNameValidator() {
        return currencyNameValidator;
    }

    public CurrencyConvertorValidator getCurrencyConvertorValidator() {
        return currencyConvertorValidator;
    }
}
