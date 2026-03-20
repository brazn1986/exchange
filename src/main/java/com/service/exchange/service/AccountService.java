package com.service.exchange.service;


import com.service.exchange.dao.AccountRepository;
import com.service.exchange.entity.Account;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AccountService {

    private AccountRepository accountRepository;
    private ValidationService validationService;

    public AccountService(AccountRepository accountRepository, ValidationService validationService) {
        this.accountRepository = accountRepository;
        this.validationService = validationService;
    }

    public void create(Account account) {
        validationService.getAccountValidator().validateExistingAccount(account);
        validationService.getAccountValidator().validate(account.getRubBalance(), account.getUsdtBalance());
        account.setReservedRubBalance(new BigDecimal(0));
        accountRepository.save(account);
    }

    public Account find(Integer userId) {
        return accountRepository.findByUserId(userId.toString());
    }
}
