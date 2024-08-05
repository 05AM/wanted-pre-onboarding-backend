package com.wanted.server.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wanted.server.infra.web.dto.response.RecruitSimpleResponse;

public interface RecruitSearchRepository {

    Page<RecruitSimpleResponse> search(String keyword, Pageable pageable);
}
