package com.wanted.server.application.service;

import org.springframework.stereotype.Service;

import com.wanted.server.application.service.command.RecruitUpdateCommand;
import com.wanted.server.domain.recruit.Recruit;
import com.wanted.server.domain.repository.RecruitRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecruitUpdateService {

    private final RecruitRepository recruitRepository;

    public void update(RecruitUpdateCommand command) {
        Recruit recruit = recruitRepository.findById(command.id());
        Recruit updated = recruit.update(
                command.position(),
                command.stack(),
                command.content(),
                command.compensation());

        recruitRepository.save(updated);
    }
}
