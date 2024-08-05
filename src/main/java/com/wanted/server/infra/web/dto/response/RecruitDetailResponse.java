package com.wanted.server.infra.web.dto.response;

import java.util.List;

import lombok.Builder;

@Builder
public record RecruitDetailResponse(
        Long id,
        String companyName,
        String nation,
        String region,
        String position,
        Integer compensation,
        String stack,
        String content,
        List<Long> relateds
) {
}
