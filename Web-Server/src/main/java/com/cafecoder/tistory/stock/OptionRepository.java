package com.cafecoder.tistory.stock;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository<Options, Long> {
    List<Options> findPNo (Long pNo);
}
