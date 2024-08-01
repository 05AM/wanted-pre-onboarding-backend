package com.wanted.server.infra.persistence.mapper;

import org.springframework.stereotype.Component;

import com.wanted.server.domain.recruit.Recruit;
import com.wanted.server.infra.persistence.jpa.entity.CompanyEntity;
import com.wanted.server.infra.persistence.jpa.entity.RecruitEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RecruitPersistenceMapper {

    private final CompanyPersistenceMapper companyPersistenceMapper;

    public RecruitEntity toEntity(Recruit domain) {
        return RecruitEntity.builder()
                .id(domain.getId())
                .position(domain.getPosition())
                .stack(domain.getStack())
                .content(domain.getContent())
                .compensation(domain.getCompensation())
                .company(CompanyEntity.builder()
                        .id(domain.getCompany().getId())
                        .build())
                .build();
    }

    public Recruit toDomain(RecruitEntity entity) {
        return Recruit.builder()
                .id(entity.getId())
                .position(entity.getPosition())
                .stack(entity.getStack())
                .content(entity.getContent())
                .compensation(entity.getCompensation())
                .company(companyPersistenceMapper.toDomain(entity.getCompany()))
                .build();
    }
}
