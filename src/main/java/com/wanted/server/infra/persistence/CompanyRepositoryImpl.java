package com.wanted.server.infra.persistence;

import org.springframework.stereotype.Repository;

import com.wanted.server.domain.repository.CompanyRepository;
import com.wanted.server.infra.persistence.jpa.repository.JpaCompanyRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CompanyRepositoryImpl implements CompanyRepository {

    private final JpaCompanyRepository jpaCompanyRepository;

    @Override
    public boolean isExist(Long id) {
        return jpaCompanyRepository.existsById(id);
    }
}
