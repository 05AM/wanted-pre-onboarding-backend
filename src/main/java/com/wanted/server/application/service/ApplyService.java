package com.wanted.server.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wanted.server.application.service.ApplyHistoryValidationService;
import com.wanted.server.application.service.RecruitValidationService;
import com.wanted.server.application.service.MemberValidationService;
import com.wanted.server.application.service.command.ApplyHistoryCreateCommand;
import com.wanted.server.domain.applyHistory.ApplyHistory;
import com.wanted.server.domain.repository.ApplyHistoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ApplyService {

    private final ApplyHistoryValidationService applyHistoryValidationService;
    private final MemberValidationService memberValidationService;
    private final RecruitValidationService recruitValidationService;

    private final ApplyHistoryRepository applyHistoryRepository;

    public void create(ApplyHistoryCreateCommand command) {
        memberValidationService.validateUserExist(command.memberId());
        recruitValidationService.validateRecruitExist(command.recruitId());
        applyHistoryValidationService.validateUserAppliedAlready(command.memberId(), command.recruitId());

        ApplyHistory recorded = ApplyHistory.record(command.memberId(), command.recruitId());
        applyHistoryRepository.save(recorded);
    }
}

