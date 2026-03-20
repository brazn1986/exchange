package com.service.exchange.service.exception;

public class AccountAlreadyExistException extends CurrencyExchangeException {
    public AccountAlreadyExistException(String message) {
        super(message);
    }
}
