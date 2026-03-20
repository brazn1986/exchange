package com.service.exchange.service.validator;

import com.service.exchange.service.exception.CurrencyExchangeException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CurrencyConvertorValidator {

    public void validate(BigDecimal requestedAmount) {
        if(requestedAmount.compareTo(BigDecimal.valueOf(5000)) < 0) {
            throw new CurrencyExchangeException("requestedAmount NOT VALID "+requestedAmount);
        }
    }
}
