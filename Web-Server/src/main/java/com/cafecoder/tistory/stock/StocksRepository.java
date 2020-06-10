package com.cafecoder.tistory.stock;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StocksRepository extends JpaRepository<Stocks, Long> {
    List<Stocks> findByUserId(String userId);
}
