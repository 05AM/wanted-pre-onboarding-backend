package com.wanted.server.infra.persistence.mapper;

import org.springframework.stereotype.Component;

import com.wanted.server.domain.recruit.Recruit;
import com.wanted.server.infra.persistence.jpa.entity.CompanyEntity;
import com.wanted.server.infra.persistence.jpa.entity.RecruitEntity;

@Component
public class RecruitPersistenceMapper {

    public RecruitEntity toEntity(Recruit domain) {
        return RecruitEntity.builder()
                .id(domain.getId())
                .position(domain.getPosition())
                .stack(domain.getStack())
                .content(domain.getContent())
                .compensation(domain.getCompensation())
                .company(CompanyEntity.builder()
                        .id(domain.getCompanyId())
                        .build())
                .build();
    }
}
