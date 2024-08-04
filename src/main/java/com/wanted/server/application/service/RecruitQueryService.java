package com.wanted.server.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wanted.server.domain.recruit.Recruit;
import com.wanted.server.domain.repository.RecruitRepository;
import com.wanted.server.infra.web.dto.response.RecruitDetailResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecruitQueryService {

    private final RecruitRepository recruitRepository;

    public RecruitDetailResponse getDetail(Long recruitId) {
        Recruit recruit = recruitRepository.findById(recruitId);
        List<Long> relatedIds = getRelatedIdsExceptMe(recruitId, recruit.getCompany().getId());

        return RecruitDetailResponse.builder()
                .id(recruit.getId())
                .companyName(recruit.getCompany().getName())
                .nation(recruit.getCompany().getNation())
                .region(recruit.getCompany().getRegion())
                .position(recruit.getPosition())
                .compensation(recruit.getCompensation())
                .stack(recruit.getStack())
                .relateds(relatedIds)
                .build();
    }

    private List<Long> getRelatedIdsExceptMe(Long recruitId, Long companyId) {
        List<Recruit> relateds = recruitRepository.findByCompanyId(companyId);

        return relateds.stream()
                .filter(related -> related.getId() != recruitId)
                .mapToLong(Recruit::getId)
                .boxed()
                .toList();
    }
}
