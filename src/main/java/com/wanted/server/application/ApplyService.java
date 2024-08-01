package com.wanted.server.application;

import org.springframework.stereotype.Service;

import com.wanted.server.application.service.ApplyHistoryValidationService;
import com.wanted.server.application.service.RecruitValidationService;
import com.wanted.server.application.service.MemberValidationService;
import com.wanted.server.application.service.command.ApplyHistoryCreateCommand;
import com.wanted.server.domain.applyHistory.ApplyHistory;
import com.wanted.server.domain.repository.ApplyHistoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
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
