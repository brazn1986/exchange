package com.service.exchange.dao;

import com.service.exchange.entity.ConversionRequestHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;


@Repository
public interface ConversionRequestHistoryRepository extends JpaRepository<ConversionRequestHistory, String> {

    ConversionRequestHistory findByUserIdAndPrice(String userId, BigDecimal price);


}
