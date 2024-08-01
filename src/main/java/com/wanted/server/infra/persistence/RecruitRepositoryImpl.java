package com.wanted.server.infra.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wanted.server.common.exception.model.NotExistException;
import com.wanted.server.common.response.StatusCode;
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
    public Recruit findById(Long id) {
        RecruitEntity recruitEntity = jpaRecruitRepository.findById(id)
                .orElseThrow(() -> new NotExistException(StatusCode.RECRUIT_NOT_FOUND_ERROR));

        return mapper.toDomain(recruitEntity);
    }

    @Override
    public List<Recruit> findByCompanyId(Long companyId) {
        return jpaRecruitRepository.findByCompanyId(companyId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public boolean existById(Long id) {
        return jpaRecruitRepository.existsById(id);
    }

    @Override
    public void save(Recruit recruit) {
        RecruitEntity recruitEntity = mapper.toEntity(recruit);

        jpaRecruitRepository.save(recruitEntity);
    }

    @Override
    public void delete(Recruit recruit) {
        RecruitEntity recruitEntity = mapper.toEntity(recruit);

        jpaRecruitRepository.delete(recruitEntity);
    }
}
