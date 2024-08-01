package com.wanted.server.domain.repository;

import java.util.List;

import com.wanted.server.domain.recruit.Recruit;

public interface RecruitRepository {

    Recruit findById(Long id);

    List<Recruit> findByCompanyId(Long companyId);

    boolean existById(Long id);

    void save(Recruit recruit);

    void delete(Recruit recruit);
}
