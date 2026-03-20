package com.service.exchange.controller;

import com.service.exchange.dto.CreateOrderRequestDto;
import com.service.exchange.dto.OrderDto;
import com.service.exchange.entity.Order;
import com.service.exchange.entity.OrderStatus;
import com.service.exchange.model.CurrencyPair;
import com.service.exchange.service.OrderService;
import com.service.exchange.service.validator.CurrencyNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.service.exchange.utils.CurrencyNameUtil.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private CurrencyNameValidator currencyNameValidator;

    @PostMapping
    public void create(@RequestBody CreateOrderRequestDto createOrderDto) {

        currencyNameValidator.validate(createOrderDto.getCurrencyIn(), createOrderDto.getCurrencyOut());

        CurrencyPair currencyPair = getCurrencyPair(createOrderDto.getCurrencyIn(), createOrderDto.getCurrencyOut());
        orderService.createOrder(createOrderDto.getUserId(), currencyPair, createOrderDto.getAmount(), createOrderDto.getPrice());
    }

    @GetMapping
    public List<OrderDto> findOrders(@RequestParam String status) {
        return orderService.findOrders(OrderStatus.valueOf(status)).stream().map(this::convert).collect(Collectors.toList());
    }

    @DeleteMapping
    public void cancelOrder(@RequestParam String orderId) {
         orderService.cancelOrder(orderId);
    }

    private OrderDto convert(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setCreatedAt(order.getCreatedAt());
        orderDto.setInAmount(order.getInAmount());
        orderDto.setOutAmount(order.getOutAmount());
        orderDto.setCurrencyPair(order.getCurrencyPair());
        orderDto.setPrice(order.getPrice());
        return orderDto;
    }
}
