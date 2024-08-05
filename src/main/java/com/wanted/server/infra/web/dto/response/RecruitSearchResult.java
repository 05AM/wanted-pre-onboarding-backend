package com.wanted.server.infra.web.dto.response;

import java.util.List;

import lombok.Builder;

@Builder
public record RecruitSearchResult(
        List<RecruitSimpleResponse> recruits,

        PageInfo pageInfo
) {
}
