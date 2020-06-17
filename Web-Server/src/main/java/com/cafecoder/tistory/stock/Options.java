package com.cafecoder.tistory.stock;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Options {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long pNo;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String size;

    @Column(nullable = true)
    Long amount;

    @Builder
    public Options (Long pNo, String color, String size, Long amount) {
        this.pNo = pNo;
        this.color = color;
        this.size = size;

        if(amount == null) {
            this.amount = null;
        }
        else {
            this.amount = amount;
        }
    }
}
