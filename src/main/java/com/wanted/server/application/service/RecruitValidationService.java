package com.wanted.server.application.service;

import org.springframework.stereotype.Service;

import com.wanted.server.common.exception.model.NotExistException;
import com.wanted.server.common.response.StatusCode;
import com.wanted.server.domain.repository.RecruitRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecruitValidationService {
    private final RecruitRepository recruitRepository;

    public void validateRecruitExist(Long recruitId) {
        if (!recruitRepository.existById(recruitId)) {
            throw new NotExistException(StatusCode.RECRUIT_NOT_FOUND_ERROR);
        }
    }
}
