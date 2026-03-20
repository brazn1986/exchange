package com.service.exchange.service.validator;

import com.service.exchange.dao.AccountRepository;
import com.service.exchange.service.exception.AccountInputDataException;
import com.service.exchange.service.exception.AccountNotFoundException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AccountValidator {

    private AccountRepository accountRepository;

    public AccountValidator(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void validate(String userId) {
        if(accountRepository.findByUserId(userId) == null) {
            throw new AccountNotFoundException("NO ACCOUNT FOUND FOR USER");
        }
    }

    public void validate(BigDecimal rubBalance, BigDecimal usdtBalance) {
        if(rubBalance == null || usdtBalance == null) {
            throw new AccountInputDataException("BALANCE INPUT NOT CORRECT");
        }
    }

}
