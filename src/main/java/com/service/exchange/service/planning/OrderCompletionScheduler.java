package com.service.exchange.service.planning;

import com.service.exchange.entity.OrderStatus;
import com.service.exchange.service.OrderExecutionService;
import com.service.exchange.service.OrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
public class OrderCompletionScheduler {

    private OrderService orderService;

    private OrderExecutionService orderExecutionService;

    @Value("${app.scheduled.order.complete-min}")
    private long minutesToComplete;

    public OrderCompletionScheduler(OrderService orderService, OrderExecutionService orderExecutionService) {
        this.orderService = orderService;
        this.orderExecutionService = orderExecutionService;
    }

    @Scheduled(fixedRateString = "${scheduled.order.fixed-rate:5000}")
    public void checkOrderAndComplete() {

        Instant now = Instant.now();

        orderService.findOrders(OrderStatus.ACTIVE).forEach(order->{
            Duration duration = Duration.between(order.getCreatedAt(), now);
            long minutesPassed = duration.toMinutes();

            if(minutesPassed >= minutesToComplete) {
                orderExecutionService.complete(order);
            }
        });


    }


}
