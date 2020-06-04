package com.cafecoder.tistory.files;

import com.cafecoder.tistory.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StocksRepository extends JpaRepository<Stocks, Long> {
    Stocks findByUserIdAndProductNameAndColorAndSize(String userId, String productName, String color, String size);
    List<Stocks> findByUserId(String userId);
}
