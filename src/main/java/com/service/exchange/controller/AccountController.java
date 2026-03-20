package com.service.exchange.controller;

import com.service.exchange.dto.AccountDto;
import com.service.exchange.entity.Account;
import com.service.exchange.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public void create(@RequestBody AccountDto accountDto) {
        accountService.create(convert(accountDto));
    }

    @GetMapping
    public @ResponseBody AccountDto getAccount(@RequestParam Integer userId) {
        return convert(accountService.find(userId));
    }

    private Account convert(AccountDto accountDto) {
        Account account = new Account();
        account.setUserId(accountDto.getUserId());
        account.setRubBalance(accountDto.getRubBalance());
        account.setUsdtBalance(accountDto.getUsdtBalance());
        return account;
    }

    private AccountDto convert(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setRubBalance(account.getRubBalance());
        accountDto.setUsdtBalance(account.getUsdtBalance());
        return accountDto;
    }
}
