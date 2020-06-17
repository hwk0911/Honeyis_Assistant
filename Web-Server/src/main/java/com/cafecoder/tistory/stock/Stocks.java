package com.cafecoder.tistory.stock;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
public class Stocks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long uNo;

    @Column(nullable = false)
    private Long cNo;

    @Column(nullable = false)
    private String productName;

    @Builder
    public Stocks(Long uNo, Long cNo, String productName) {
        this.uNo = uNo;
        this.cNo = cNo;
        this.productName = productName;
    }
}
