package com.service.exchange.dao;

import com.service.exchange.entity.Account;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository  extends JpaRepository<Account, String> {

    @Cacheable(value = "accountByUserId", key = "#userId")
    Account findByUserId(String userId);

    @CacheEvict(value = "accountByUserId", allEntries = true)
    Account save(Account account);
}
