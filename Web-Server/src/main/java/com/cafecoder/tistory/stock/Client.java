package com.cafecoder.tistory.stock;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String client;

    @Column(nullable = false)
    private Long uNo;

    @Builder
    public Client(String client, Long uNo) {
        this.client = client;
        this.uNo = uNo;
    }
}
