package com.wanted.server.infra.persistence.mapper;

import org.springframework.stereotype.Component;

import com.wanted.server.domain.applyHistory.ApplyHistory;
import com.wanted.server.infra.persistence.jpa.entity.ApplyHistoryEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ApplyHistoryPersistenceMapper {

    private final MemberPersistenceMapper memberPersistenceMapper;
    private final RecruitPersistenceMapper recruitPersistenceMapper;

    public ApplyHistoryEntity toEntity(ApplyHistory domain) {
        return ApplyHistoryEntity.builder()
                .id(domain.getId())
                .member(memberPersistenceMapper.toEntity(domain.getMember()))
                .recruit(recruitPersistenceMapper.toEntity(domain.getRecruit()))
                .build();
    }
}
