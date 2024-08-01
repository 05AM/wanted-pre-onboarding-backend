package com.wanted.server.application.service;

import org.springframework.stereotype.Service;

import com.wanted.server.domain.recruit.Recruit;
import com.wanted.server.domain.repository.RecruitRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecruitDeleteService {

    private final RecruitRepository recruitRepository;

    public void delete(Long recruitId) {
        Recruit recruit = recruitRepository.findById(recruitId);

        recruitRepository.delete(recruit);
    }
}
