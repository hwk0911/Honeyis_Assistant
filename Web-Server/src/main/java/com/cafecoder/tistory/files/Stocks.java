package com.cafecoder.tistory.files;

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
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String productName;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String color;

    @Column(columnDefinition = "TEXT")
    private String size;

    @Column(columnDefinition = "INT")
    private int amount;
}
