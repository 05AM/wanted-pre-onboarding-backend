package com.wanted.server.domain.repository;

import com.wanted.server.domain.recruit.Recruit;

public interface RecruitRepository {

    Recruit findById(Long id);

    void save(Recruit recruit);
}
