package com.cafecoder.tistory.product;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String userId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String password;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String company;

    @Builder
    public Products(String userId, String password, String company) {
        this.userId = userId;
        this.password = password;
        this.company = company;
    }
}