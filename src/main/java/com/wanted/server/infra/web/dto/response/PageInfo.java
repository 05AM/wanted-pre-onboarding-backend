package com.wanted.server.infra.web.dto.response;

import lombok.Builder;

@Builder
public record PageInfo(
        int page,
        int size,
        long totalElements,
        int totalPages
) {
}
