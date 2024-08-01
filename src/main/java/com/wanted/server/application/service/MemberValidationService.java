package com.wanted.server.application.service;

import org.springframework.stereotype.Service;

import com.wanted.server.common.exception.model.NotExistException;
import com.wanted.server.common.response.StatusCode;
import com.wanted.server.domain.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberValidationService {

    private final MemberRepository memberRepository;

    public void validateUserExist(Long userId) {
        if (!memberRepository.existById(userId)) {
            throw new NotExistException(StatusCode.USER_NOT_FOUND_ERROR);
        }
    }
}
