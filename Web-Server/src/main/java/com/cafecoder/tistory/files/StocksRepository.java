package com.cafecoder.tistory.files;

import com.cafecoder.tistory.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StocksRepository extends JpaRepository<Stocks, String> {
    Stocks findByUserId(String userId);
}
