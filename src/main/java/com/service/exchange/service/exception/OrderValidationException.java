package com.service.exchange.service.exception;

public class OrderValidationException extends CurrencyExchangeException {

    public OrderValidationException(String message) {
        super(message);
    }
}
