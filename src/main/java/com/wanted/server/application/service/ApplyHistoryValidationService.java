package com.wanted.server.application.service;

import org.springframework.stereotype.Service;

import com.wanted.server.common.exception.model.InvalidStateException;
import com.wanted.server.common.response.StatusCode;
import com.wanted.server.domain.repository.ApplyHistoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplyHistoryValidationService {

    private final ApplyHistoryRepository applyHistoryRepository;

    public void validateUserAppliedAlready(Long userId, Long recruitId) {
        if (applyHistoryRepository.hasUserAlreadyApplied(userId, recruitId)) {
            throw new InvalidStateException(StatusCode.USER_ALREADY_APPLIED_ERROR);
        }
    }
}
