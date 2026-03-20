package com.service.exchange.service.exception;

public class PriceExpirationException extends CurrencyExchangeException {

    public PriceExpirationException(String message) {
        super(message);
    }
}
