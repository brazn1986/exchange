package com.service.exchange.service.validator;

import com.service.exchange.dao.ConversionRequestHistoryRepository;
import com.service.exchange.entity.ConversionRequestHistory;
import com.service.exchange.service.exception.PriceExpirationException;
import com.service.exchange.service.exception.PriceNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

@Component
public class PriceValidator {

    @Value("${app.price.expiration.time-min}")
    private long minutesToExpire;

    private final ConversionRequestHistoryRepository conversionRequestHistoryRepository;

    public PriceValidator(ConversionRequestHistoryRepository conversionRequestHistoryRepository) {
        this.conversionRequestHistoryRepository = conversionRequestHistoryRepository;
    }

    public void validate(Integer userId, BigDecimal price) {

        ConversionRequestHistory conversionRequest = conversionRequestHistoryRepository.findByUserIdAndPrice(userId.toString(), price);

        if(conversionRequest == null) {
            throw new PriceNotFoundException("NO PRICE FOUND");
        }

        Instant now = Instant.now();
        Duration duration = Duration.between(conversionRequest.getCreatedAt(), now);

        if(duration.toMinutes() > minutesToExpire) {
            throw new PriceExpirationException("PRICE EXPIRED");
        }
    }


}
