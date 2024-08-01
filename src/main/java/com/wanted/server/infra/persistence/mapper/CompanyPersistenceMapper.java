package com.wanted.server.infra.persistence.mapper;

import org.springframework.stereotype.Component;

import com.wanted.server.domain.company.Company;
import com.wanted.server.infra.persistence.jpa.entity.CompanyEntity;

@Component
public class CompanyPersistenceMapper {

    public Company toDomain(CompanyEntity entity) {
        return Company.builder()
                .id(entity.getId())
                .name(entity.getName())
                .nation(entity.getNation())
                .region(entity.getRegion())
                .build();
    }
}
