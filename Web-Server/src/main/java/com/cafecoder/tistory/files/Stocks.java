package com.cafecoder.tistory.files;

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

    @Column(columnDefinition = "TEXT", nullable = false)
    private String userId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String client;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String productName;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String color;

    @Column(columnDefinition = "TEXT")
    private String size;

    @Column(columnDefinition = "INT")
    private int amount;

    @Builder
    public Stocks(String userId, String client, String productName, String color, String size, int amount) {
        this.client = client;
        this.userId = userId;
        this.productName = productName;
        this.color = color;

        if(size == null) {
            this.size = "FREE";
        }
        else {
            this.size = size;
        }
        this.amount = amount;
    }
}
