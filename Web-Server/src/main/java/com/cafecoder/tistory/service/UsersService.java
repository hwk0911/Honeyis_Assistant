package com.cafecoder.tistory.service;

import com.cafecoder.tistory.user.UsersRepository;
import com.cafecoder.tistory.user.dto.UsersSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UsersService {
    private  final UsersRepository usersRepository;

    @Transactional
    public Long save (UsersSaveRequestDto requestDto) {
        return usersRepository.save(requestDto.toEntity()).getId();
    }
}
