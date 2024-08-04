package com.wanted.server.infra.persistence.mapper;

import org.springframework.stereotype.Component;

import com.wanted.server.domain.applyHistory.ApplyHistory;
import com.wanted.server.infra.persistence.jpa.entity.ApplyHistoryEntity;
import com.wanted.server.infra.persistence.jpa.entity.MemberEntity;
import com.wanted.server.infra.persistence.jpa.entity.RecruitEntity;

@Component
public class ApplyHistoryPersistenceMapper {

    public ApplyHistoryEntity toEntity(ApplyHistory domain) {
        return ApplyHistoryEntity.builder()
                .id(domain.getId())
                .member(MemberEntity.builder()
                        .id(domain.getMember().getId())
                        .build())
                .recruit(RecruitEntity.builder()
                        .id(domain.getRecruit().getId())
                        .build())
                .build();
    }
}
