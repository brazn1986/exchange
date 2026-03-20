package com.service.exchange.service;

import com.service.exchange.dao.AccountRepository;
import com.service.exchange.dao.OrderRepository;
import com.service.exchange.entity.Account;
import com.service.exchange.entity.Order;
import com.service.exchange.entity.OrderStatus;
import com.service.exchange.model.CurrencyPair;
import com.service.exchange.service.convertor.ICurrencyConvertor;
import com.service.exchange.utils.CurrencyUtil;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Component
public class OrderService {

    private final Map<String, ICurrencyConvertor> currencyConvertors;
    private final OrderRepository orderRepository;
    private final ValidationService validationService;
    private final AccountRepository accountRepository;

    public OrderService(ValidationService validationService,
                        Map<String, ICurrencyConvertor> currencyConvertors,
                        OrderRepository orderRepository,
                        AccountRepository accountRepository) {
        this.validationService = validationService;
        this.currencyConvertors = currencyConvertors;
        this.orderRepository = orderRepository;
        this.accountRepository = accountRepository;
    }

    public List<Order> findOrders(OrderStatus orderStatus) {
        return orderRepository.findByStatus(orderStatus.name());
    }

    @Transactional
    public void cancelOrder(String orderId) {

        Order order = orderRepository.findById(orderId).get();
        validationService.getCreateOrderValidator().validateOrderChangeableState(order);
        order.setStatus(OrderStatus.CLOSED.name());
        orderRepository.save(order);

        Account account = accountRepository.findByUserId(order.getUserId());
        BigDecimal newReservedSum = account.getReservedRubBalance().subtract(order.getInAmount());
        account.setReservedRubBalance(newReservedSum);

        accountRepository.save(account);
    }

    @Transactional
    public void createOrder(Integer userId, CurrencyPair currencyPair, BigDecimal inAmount, BigDecimal price) {

        inAmount = CurrencyUtil.prepareRounded(inAmount);
        validationService.getAccountValidator().validate(userId.toString());
        validationService.getPriceValidator().validate(userId, price);

        BigDecimal outAmount = currencyConvertors.get(currencyPair.name()).convert(inAmount, price);

        Order order = new Order();
        order.setUserId(userId.toString());
        order.setInAmount(inAmount);
        order.setOutAmount(outAmount);
        order.setPrice(price);
        order.setCurrencyPair(currencyPair.name());
        order.setStatus(OrderStatus.ACTIVE.name());

        validationService.getCreateOrderValidator().validate(order);

        orderRepository.save(order);

        Account account = accountRepository.findByUserId(userId.toString());
        BigDecimal newReservedSum = account.getReservedRubBalance().add(inAmount);
        account.setReservedRubBalance(newReservedSum);

        accountRepository.save(account);
    }

}
