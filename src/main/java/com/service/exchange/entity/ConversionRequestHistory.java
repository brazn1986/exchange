package com.service.exchange.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "ask_conv_history")
public class ConversionRequestHistory {

    @Id
    private String userId;

    @UpdateTimestamp
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "price", precision = 8, scale = 3)
    private BigDecimal price;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
