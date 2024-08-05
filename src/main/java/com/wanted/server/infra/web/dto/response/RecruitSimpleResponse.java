package com.wanted.server.infra.web.dto.response;

import java.time.LocalDateTime;

public record RecruitSimpleResponse(
        Long id,
        String companyName,
        String nation,
        String region,
        String position,
        Integer compensation,
        String stack,
        LocalDateTime createdAt
) {
}
