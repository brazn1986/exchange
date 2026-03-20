package com.service.exchange.service.exception;

public class PriceNotFoundException extends CurrencyExchangeException {

    public PriceNotFoundException(String message) {
        super(message);
    }
}
