package com.service.exchange.service;

import com.service.exchange.dao.ConversionRequestHistoryRepository;
import org.springframework.stereotype.Component;


@Component
public class ConvertionHistoryService {

    private ConversionRequestHistoryRepository conversionRequestHistoryRepository;

    public ConvertionHistoryService(ConversionRequestHistoryRepository conversionRequestHistoryRepository) {
        this.conversionRequestHistoryRepository = conversionRequestHistoryRepository;
    }


}
