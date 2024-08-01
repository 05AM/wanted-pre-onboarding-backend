package com.wanted.server.application.service;

import org.springframework.stereotype.Service;

import com.wanted.server.common.exception.model.NotExistException;
import com.wanted.server.common.response.StatusCode;
import com.wanted.server.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserValidationService {

    private final UserRepository userRepository;

    public void validateUserExist(Long userId) {
        if (!userRepository.existById(userId)) {
            throw new NotExistException(StatusCode.USER_NOT_FOUND_ERROR);
        }
    }
}
