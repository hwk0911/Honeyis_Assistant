package com.cafecoder.tistory.files;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@NoArgsConstructor
@Entity
@Getter
public class Stocks {

    @Column(columnDefinition = "TEXT", nullable = false)
    private String userId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String productName;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String color;

    @Column(columnDefinition = "TEXT")
    private String size;

    @Column(columnDefinition = "INT")
    private int amount;
}
