package com.wanted.server.infra.persistence.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wanted.server.infra.persistence.jpa.entity.RecruitEntity;
import com.wanted.server.infra.web.dto.response.RecruitSimpleResponse;

public interface JpaRecruitRepository extends JpaRepository<RecruitEntity, Long> {

    List<RecruitEntity> findByCompanyId(Long companyId);

    @Query("SELECT new com.wanted.server.infra.web.dto.response.RecruitSimpleResponse(" +
            "r.id, r.company.name, r.company.nation, r.company.region, r.position, r.compensation, r.stack, r.createdAt) " +
            "FROM RecruitEntity r")
    Page<RecruitSimpleResponse> search(Pageable pageable);

    @Query("SELECT new com.wanted.server.infra.web.dto.response.RecruitSimpleResponse(" +
            "r.id, r.company.name, r.company.nation, r.company.region, r.position, r.compensation, r.stack, r.createdAt) " +
            "FROM RecruitEntity r " +
            "WHERE LOWER(r.position) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(r.stack) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(r.company.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(r.company.nation) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(r.company.region) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<RecruitSimpleResponse> search(@Param("keyword") String keyword, Pageable pageable);
}
