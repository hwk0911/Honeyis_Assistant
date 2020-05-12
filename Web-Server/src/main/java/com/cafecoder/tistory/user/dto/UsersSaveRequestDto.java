package com.cafecoder.tistory.user.dto;

import com.cafecoder.tistory.user.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsersSaveRequestDto {
    private String userId;
    private String password;
    private String company;
    private String email;

    @Builder
    public UsersSaveRequestDto(String userId, String password, String company, String email) {
        this.userId = userId;
        this.company = company;
        this.password = password;
        this.email = email;
    }

    public Users toEntity () {
        return Users.builder()
                .userId(userId)
                .password(password)
                .company(company)
                .email(email)
                .build();
    }
}
