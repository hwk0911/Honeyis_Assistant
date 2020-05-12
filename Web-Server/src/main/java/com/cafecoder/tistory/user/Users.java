package com.cafecoder.tistory.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String userId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String password;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String company;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String email;

    @Builder
    public Users(String userId, String password, String company, String email) {
        this.userId = userId;
        this.password = password;
        this.company = company;
        this.email = email;
    }
}
