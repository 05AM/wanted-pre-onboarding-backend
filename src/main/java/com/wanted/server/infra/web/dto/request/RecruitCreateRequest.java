package com.wanted.server.infra.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RecruitCreateRequest(
        @NotNull
        Long companyId,

        @NotNull
        @NotBlank
        String position,

        @NotNull
        @NotBlank
        String stack,

        @NotNull
        @NotBlank
        String content,

        @NotNull
        Integer compensation
) {
}
