package com.service.exchange.service.validator;

import com.service.exchange.model.Currency;
import com.service.exchange.service.exception.CurrncyNameIncorrectException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CurrencyNameValidator {

    private final static List<String> CURRENCY_NAMES;

    static {
        CURRENCY_NAMES = Arrays.stream(Currency.values()).map(Enum::name).collect(Collectors.toList());
    }

    public void validate(String ... currencyNames) {
        Arrays.stream(currencyNames).forEach(cn->{
            if(!CURRENCY_NAMES.contains(cn)) {
                throw new CurrncyNameIncorrectException("CURRENCY NAME NOT FOUND: " + cn);
            }
        });
    }
}
