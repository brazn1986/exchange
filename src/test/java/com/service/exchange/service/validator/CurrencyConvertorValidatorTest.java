package com.service.exchange.service.validator;

import com.service.exchange.service.exception.CurrencyExchangeException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.fail;

public class CurrencyConvertorValidatorTest {

    private CurrencyConvertorValidator validator;

    @Before
    public void setUp() {
        validator = new CurrencyConvertorValidator();
    }

    @Test
    public void validateWithAmountEqualTo5000ShouldNotThrowException() {
        BigDecimal amount = BigDecimal.valueOf(5000);

        try {
            validator.validate(amount);
        } catch (CurrencyExchangeException e) {
            fail("Should not throw exception for amount 5000");
        }
    }

    @Test
    public void validateWithAmountGreaterThan5000ShouldNotThrowException() {
        BigDecimal amount = BigDecimal.valueOf(10000);

        try {
            validator.validate(amount);
        } catch (CurrencyExchangeException e) {
            fail("Should not throw exception for amount greater than 5000");
        }
    }

    @Test(expected = CurrencyExchangeException.class)
    public void validateWithAmountLessThan5000ShouldThrowException() {
        BigDecimal amount = BigDecimal.valueOf(390);
        validator.validate(amount);
    }

}
