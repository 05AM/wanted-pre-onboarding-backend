package com.wanted.server.infra.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wanted.server.infra.persistence.jpa.entity.CompanyEntity;

public interface JpaCompanyRepository extends JpaRepository<CompanyEntity, Long> {

    boolean existsById(Long id);
}
