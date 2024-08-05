package com.wanted.server.application.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wanted.server.domain.recruit.Recruit;
import com.wanted.server.domain.repository.RecruitRepository;
import com.wanted.server.domain.repository.RecruitSearchRepository;
import com.wanted.server.infra.web.dto.response.PageInfo;
import com.wanted.server.infra.web.dto.response.RecruitDetailResponse;
import com.wanted.server.infra.web.dto.response.RecruitSearchResult;
import com.wanted.server.infra.web.dto.response.RecruitSimpleResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecruitQueryService {

    private final RecruitRepository recruitRepository;
    private final RecruitSearchRepository recruitSearchRepository;

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

    public RecruitSearchResult search(String keyword, Pageable pageable) {
        Page<RecruitSimpleResponse> result = recruitSearchRepository.search(keyword, pageable);

        return RecruitSearchResult.builder()
                .recruits(result.toList())
                .pageInfo(PageInfo.builder()
                        .page(pageable.getPageNumber())
                        .size(pageable.getPageSize())
                        .totalElements(result.getTotalElements())
                        .totalPages(result.getTotalPages())
                        .build())
                .build();
    }
}
