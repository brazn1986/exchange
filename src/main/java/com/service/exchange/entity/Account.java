package com.service.exchange.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @UpdateTimestamp
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "rubAmount")
    private BigDecimal rubBalance;

    @Column(name = "usdtAmount", precision = 12, scale = 8)
    private BigDecimal usdtBalance;

    @Column(name = "reservedRubBalance")
    private BigDecimal reservedRubBalance;

    @Column
    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getRubBalance() {
        return rubBalance;
    }

    public void setRubBalance(BigDecimal rubBalance) {
        this.rubBalance = rubBalance;
    }

    public BigDecimal getUsdtBalance() {
        return usdtBalance;
    }

    public void setUsdtBalance(BigDecimal usdtBalance) {
        this.usdtBalance = usdtBalance;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getReservedRubBalance() {
        return reservedRubBalance;
    }

    public void setReservedRubBalance(BigDecimal reservedRubBalance) {
        this.reservedRubBalance = reservedRubBalance;
    }
}
