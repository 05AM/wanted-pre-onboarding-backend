package com.wanted.server.infra.persistence.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wanted.server.infra.persistence.jpa.entity.RecruitEntity;

public interface JpaRecruitRepository extends JpaRepository<RecruitEntity, Long> {

    List<RecruitEntity> findByCompanyId(Long companyId);
}
