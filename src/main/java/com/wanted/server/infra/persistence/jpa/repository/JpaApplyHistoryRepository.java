package com.wanted.server.infra.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wanted.server.infra.persistence.jpa.entity.ApplyHistoryEntity;

public interface JpaApplyHistoryRepository extends JpaRepository<ApplyHistoryEntity, Long> {

    boolean existsByMemberIdAndRecruitId(Long memberId, Long recruitId);
}
