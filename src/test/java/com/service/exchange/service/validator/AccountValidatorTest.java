package com.service.exchange.service.validator;

import com.service.exchange.dao.AccountRepository;
import com.service.exchange.entity.Account;
import com.service.exchange.service.exception.AccountInputDataException;
import com.service.exchange.service.exception.AccountNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

public class AccountValidatorTest {

    private AccountRepository accountRepository;
    private AccountValidator accountValidator;

    @Before
    public void setUp() {
        accountRepository = Mockito.mock(AccountRepository.class);
        accountValidator = new AccountValidator(accountRepository);
    }

    @Test
    public void validateByUserIdWhenAccountExistsShouldNotThrowException() {

        String userId = "user123";
        Account mockAccount = new Account();
        when(accountRepository.findByUserId(userId)).thenReturn(mockAccount);

        try {
            accountValidator.validate(userId);
        } catch (AccountNotFoundException e) {
            fail("Should not throw AccountNotFoundException for existing user");
        }

        verify(accountRepository, times(1)).findByUserId(userId);
    }

    @Test(expected = AccountNotFoundException.class)
    public void validateByUserIdWhenAccountNotFoundShouldThrowAccountNotFoundException() {

        String userId = "nonexistent";
        when(accountRepository.findByUserId(userId)).thenReturn(null);

        accountValidator.validate(userId);

        verify(accountRepository, times(1)).findByUserId(userId);
    }

    @Test
    public void validateByUserIdWhenAccountNotFoundShouldThrowExceptionWithCorrectMessage() {

        String userId = "nonexistent";
        when(accountRepository.findByUserId(userId)).thenReturn(null);

        try {
            accountValidator.validate(userId);
            fail("Expected AccountNotFoundException");
        } catch (AccountNotFoundException e) {
            assertEquals("NO ACCOUNT FOUND FOR USER", e.getMessage());
        }

        verify(accountRepository, times(1)).findByUserId(userId);
    }

    @Test(expected = AccountNotFoundException.class)
    public void validateByUserIdWithEmptyUserIdShouldThrowAccountNotFoundException() {

        String userId = "";
        when(accountRepository.findByUserId(userId)).thenReturn(null);

        accountValidator.validate(userId);

        verify(accountRepository, times(1)).findByUserId(userId);
    }

    @Test(expected = AccountNotFoundException.class)
    public void validateByUserIdWithNullUserIdShouldThrowAccountNotFoundException() {

        when(accountRepository.findByUserId(null)).thenReturn(null);
        accountValidator.validate(null);
        verify(accountRepository, times(1)).findByUserId(null);
    }

    @Test
    public void validateByUserIdShouldCallRepositoryExactlyOnce() {

        String userId = "user123";
        Account mockAccount = new Account();
        when(accountRepository.findByUserId(userId)).thenReturn(mockAccount);

        accountValidator.validate(userId);

        verify(accountRepository, times(1)).findByUserId(userId);
        verifyNoMoreInteractions(accountRepository);
    }

    @Test
    public void validateByBalancesWhenBothBalancesNotNullShouldNotThrowException() {

        BigDecimal rubBalance = BigDecimal.valueOf(1000);
        BigDecimal usdtBalance = BigDecimal.valueOf(500);

        try {
            accountValidator.validate(rubBalance, usdtBalance);
        } catch (AccountInputDataException e) {
            fail("Should not throw AccountInputDataException when both balances are not null");
        }
    }

    @Test(expected = AccountInputDataException.class)
    public void validateByBalancesWhenRubBalanceIsNullShouldThrowAccountInputDataException() {

        BigDecimal usdtBalance = BigDecimal.valueOf(500);
        accountValidator.validate(null, usdtBalance);
    }

    @Test
    public void validateByBalancesWhenRubBalanceIsNullShouldThrowExceptionWithCorrectMessage() {
        BigDecimal usdtBalance = BigDecimal.valueOf(500);

        try {
            accountValidator.validate(null, usdtBalance);
            fail("Expected AccountInputDataException");
        } catch (AccountInputDataException e) {
            assertEquals("BALANCE INPUT NOT CORRECT", e.getMessage());
        }
    }

    @Test(expected = AccountInputDataException.class)
    public void validateByBalancesWhenUsdtBalanceIsNullShouldThrowAccountInputDataException() {

        BigDecimal rubBalance = BigDecimal.valueOf(1000);
        accountValidator.validate(rubBalance, null);
    }

    @Test
    public void validateByBalancesWhenUsdtBalanceIsNullShouldThrowExceptionWithCorrectMessage() {

        BigDecimal rubBalance = BigDecimal.valueOf(1000);

        try {
            accountValidator.validate(rubBalance, null);
            fail("Expected AccountInputDataException");
        } catch (AccountInputDataException e) {
            assertEquals("BALANCE INPUT NOT CORRECT", e.getMessage());
        }
    }

    @Test(expected = AccountInputDataException.class)
    public void validateByBalancesWhenBothBalancesAreNullShouldThrowAccountInputDataException() {
        accountValidator.validate(null, null);
    }

    @Test
    public void validateByBalancesWithZeroBalancesShouldNotThrowException() {

        BigDecimal rubBalance = BigDecimal.ZERO;
        BigDecimal usdtBalance = BigDecimal.ZERO;

        try {
            accountValidator.validate(rubBalance, usdtBalance);
        } catch (AccountInputDataException e) {
            fail("Should not throw exception for zero balances");
        }
    }

    @Test
    public void validateByBalancesWithNegativeBalancesShouldNotThrowException() {

        BigDecimal rubBalance = BigDecimal.valueOf(-1000);
        BigDecimal usdtBalance = BigDecimal.valueOf(-500);

        try {
            accountValidator.validate(rubBalance, usdtBalance);
        } catch (AccountInputDataException e) {
            fail("Should not throw exception for negative balances");
        }
    }

    @Test
    public void validateByBalancesWithDecimalBalancesShouldNotThrowException() {

        BigDecimal rubBalance = new BigDecimal("1234.56");
        BigDecimal usdtBalance = new BigDecimal("789.12");

        try {
            accountValidator.validate(rubBalance, usdtBalance);
        } catch (AccountInputDataException e) {
            fail("Should not throw exception for decimal balances");
        }
    }

    @Test
    public void validateByBalancesWithLargeBalancesShouldNotThrowException() {

        BigDecimal rubBalance = new BigDecimal("999999999.99");
        BigDecimal usdtBalance = new BigDecimal("888888888.88");

        try {
            accountValidator.validate(rubBalance, usdtBalance);
        } catch (AccountInputDataException e) {
            fail("Should not throw exception for large balances");
        }
    }
}
