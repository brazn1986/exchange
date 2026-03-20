package com.service.exchange.service.exception;

public class AccountNotFoundException extends CurrencyExchangeException {
    public AccountNotFoundException(String message) {
        super(message);
    }
}
