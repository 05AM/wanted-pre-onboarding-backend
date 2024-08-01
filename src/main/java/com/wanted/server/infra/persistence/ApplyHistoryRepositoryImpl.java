package com.wanted.server.infra.persistence;

import org.springframework.stereotype.Repository;

import com.wanted.server.domain.applyHistory.ApplyHistory;
import com.wanted.server.domain.repository.ApplyHistoryRepository;
import com.wanted.server.infra.persistence.jpa.entity.ApplyHistoryEntity;
import com.wanted.server.infra.persistence.jpa.repository.JpaApplyHistoryRepository;
import com.wanted.server.infra.persistence.mapper.ApplyHistoryPersistenceMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ApplyHistoryRepositoryImpl implements ApplyHistoryRepository {

    private final JpaApplyHistoryRepository jpaApplyHistoryRepository;
    private final ApplyHistoryPersistenceMapper mapper;

    @Override
    public void save(ApplyHistory applyHistory) {
        ApplyHistoryEntity applyHistoryEntity = mapper.toEntity(applyHistory);

        jpaApplyHistoryRepository.save(applyHistoryEntity);
    }

    @Override
    public boolean hasMemberAlreadyApplied(Long memberId, Long recruitId) {
        return jpaApplyHistoryRepository.existsByMemberIdAndRecruitId(memberId, recruitId);
    }
}
