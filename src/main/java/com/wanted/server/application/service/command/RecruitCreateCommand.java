package com.wanted.server.application.service.command;

import lombok.Builder;

@Builder
public record RecruitCreateCommand(
        Long companyId,
        String position,
        String stack,
        String content,
        Integer compensation
) {
}
