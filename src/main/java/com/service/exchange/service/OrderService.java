package com.service.exchange.service;

import com.service.exchange.dao.OrderRepository;
import com.service.exchange.entity.Order;
import com.service.exchange.entity.OrderStatus;
import com.service.exchange.model.CurrencyPair;
import com.service.exchange.service.convertor.ICurrencyConvertor;
import com.service.exchange.utils.CurrencyUtil;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Component
public class OrderService {

    private final Map<String, ICurrencyConvertor> currencyConvertors;
    private final OrderRepository orderRepository;
    private final ValidationService validationService;

    public OrderService(ValidationService validationService,
                        Map<String, ICurrencyConvertor> currencyConvertors,
                        OrderRepository orderRepository
    ) {
        this.validationService = validationService;
        this.currencyConvertors = currencyConvertors;
        this.orderRepository = orderRepository;
    }

    public List<Order> findOrders(OrderStatus orderStatus) {
        return orderRepository.findByStatus(orderStatus.name());
    }

    public void cancelOrder(String orderId) {

        Order order = orderRepository.findById(orderId).get();
        validationService.getCreateOrderValidator().validateOrderChangeableState(order);
        order.setStatus(OrderStatus.CLOSED.name());
        orderRepository.save(order);
    }

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

    }

}
