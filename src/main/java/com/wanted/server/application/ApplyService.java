package com.wanted.server.application;

import org.springframework.stereotype.Service;

import com.wanted.server.application.service.ApplyHistoryValidationService;
import com.wanted.server.application.service.RecruitValidationService;
import com.wanted.server.application.service.UserValidationService;
import com.wanted.server.application.service.command.ApplyHistoryCreateCommand;
import com.wanted.server.domain.applyHistory.ApplyHistory;
import com.wanted.server.domain.repository.ApplyHistoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplyService {

    private final ApplyHistoryValidationService applyHistoryValidationService;
    private final UserValidationService userValidationService;
    private final RecruitValidationService recruitValidationService;

    private final ApplyHistoryRepository applyHistoryRepository;

    public void create(ApplyHistoryCreateCommand command) {
        userValidationService.validateUserExist(command.userId());
        recruitValidationService.validateRecruitExist(command.recruitId());
        applyHistoryValidationService.validateUserAppliedAlready(command.userId(), command.recruitId());

        ApplyHistory recorded = ApplyHistory.record(command.userId(), command.recruitId());
        applyHistoryRepository.save(recorded);
    }
}
