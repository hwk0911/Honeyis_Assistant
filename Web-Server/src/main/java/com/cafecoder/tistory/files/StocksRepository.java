package com.cafecoder.tistory.files;

import com.cafecoder.tistory.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StocksRepository extends JpaRepository<Stocks, Long> {
    List<Stocks> findByUserId(String userId);
}
