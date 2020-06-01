package com.cafecoder.tistory.files;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Entity
@Getter
public class Stocks {

    @Id
    private String userId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String productName;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String color;

    @Column(columnDefinition = "TEXT")
    private String size;

    @Column(columnDefinition = "INT")
    private int amount;

    @Builder
    public Stocks(String userId, String productName, String color, String size, int amount) {
        this.userId = userId;
        this.productName = productName;
        this.color = color;
        this.size = size;
        this.amount = amount;
    }
}
