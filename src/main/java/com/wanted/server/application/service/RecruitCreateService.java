package com.wanted.server.application.service;

import org.springframework.stereotype.Service;

import com.wanted.server.application.service.command.RecruitCreateCommand;
import com.wanted.server.domain.recruit.Recruit;
import com.wanted.server.domain.repository.RecruitRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecruitCreateService {

    private final CompanyValidationService companyValidationService;
    private final RecruitRepository recruitRepository;

    public void create(RecruitCreateCommand command) {
        companyValidationService.validateCompanyExist(command.companyId());

        Recruit created = Recruit.create(
                command.position(),
                command.stack(),
                command.content(),
                command.compensation(),
                command.companyId()
        );

        recruitRepository.save(created);
    }
}
