package com.service.exchange.service;

import com.service.exchange.dao.AccountRepository;
import com.service.exchange.dao.OrderRepository;
import com.service.exchange.entity.Account;
import com.service.exchange.entity.Order;
import com.service.exchange.entity.OrderStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Component
public class OrderExecutionService {

    private AccountRepository accountRepository;
    private OrderRepository orderRepository;

    public OrderExecutionService(AccountRepository accountRepository, OrderRepository orderRepository) {
        this.accountRepository = accountRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public void complete(Order order) {

        order.setStatus(OrderStatus.COMPLETED.name());
        Account account = accountRepository.findByUserId(order.getUserId());

        BigDecimal updRubBalance = account.getRubBalance().subtract(order.getInAmount());
        BigDecimal updUsdtBalance = account.getUsdtBalance().add(order.getOutAmount());

        account.setRubBalance(updRubBalance);
        account.setUsdtBalance(updUsdtBalance);

        accountRepository.save(account);
        orderRepository.save(order);
    }
}
