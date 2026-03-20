package com.service.exchange.service.validator;

import com.service.exchange.dao.AccountRepository;
import com.service.exchange.entity.Account;
import com.service.exchange.entity.Order;
import com.service.exchange.entity.OrderStatus;
import com.service.exchange.service.exception.OrderValidationException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderValidator {

    private final AccountRepository accountRepository;

    public OrderValidator(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void validate(Order order) {

       if((order.getInAmount() == null || order.getOutAmount() == null) || !isInAllowedRange(order.getInAmount())) {
           throw new OrderValidationException("inAmount NOT CORRECT");
       }

      Account account = accountRepository.findByUserId(order.getUserId());

      if(!isBalanceValidForOperation(order, account)) {
         throw new OrderValidationException("RUB BALANCE TOO SMALL FOR OPERATION");
      }
    }

    private boolean isBalanceValidForOperation(Order order, Account account) {
         return order.getInAmount().compareTo(account.getRubBalance().subtract(account.getReservedRubBalance())) < 0;
    }

    public void validateOrderChangeableState(Order order) {
        if(!order.getStatus().equals(OrderStatus.ACTIVE.name())) {
            throw new OrderValidationException("ORDER CANNOT BE CHANGED , IT IS " + order.getStatus());
        }
    }

    private boolean isInAllowedRange(BigDecimal val) {
       return val.compareTo(BigDecimal.valueOf(10000)) > 0 &&
              val.compareTo(BigDecimal.valueOf(10000000)) < 0;
    }



}
