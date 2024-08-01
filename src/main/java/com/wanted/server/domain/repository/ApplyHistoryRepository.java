package com.wanted.server.domain.repository;

import com.wanted.server.domain.applyHistory.ApplyHistory;

public interface ApplyHistoryRepository {

    void save(ApplyHistory applyHistory);

    boolean hasMemberAlreadyApplied(Long memberId, Long recruitId);
}
