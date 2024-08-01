package com.wanted.server.infra.persistence;

import org.springframework.stereotype.Repository;

import com.wanted.server.domain.recruit.Recruit;
import com.wanted.server.domain.repository.RecruitRepository;
import com.wanted.server.infra.persistence.jpa.entity.RecruitEntity;
import com.wanted.server.infra.persistence.jpa.repository.JpaRecruitRepository;
import com.wanted.server.infra.persistence.mapper.RecruitPersistenceMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RecruitRepositoryImpl implements RecruitRepository {

    private final JpaRecruitRepository jpaRecruitRepository;

    private final RecruitPersistenceMapper mapper;

    @Override
    public void save(Recruit recruit) {
        RecruitEntity recruitEntity = mapper.toEntity(recruit);

        jpaRecruitRepository.save(recruitEntity);
    }
}
