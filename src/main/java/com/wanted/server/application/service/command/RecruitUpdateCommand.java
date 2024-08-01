package com.wanted.server.application.service.command;

import lombok.Builder;

@Builder
public record RecruitUpdateCommand(
        Long id,
        String position,
        String stack,
        String content,
        Integer compensation
) {
}
