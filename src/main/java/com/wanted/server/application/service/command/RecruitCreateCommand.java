package com.wanted.server.application.service.command;

public record RecruitCreateCommand(
        Long companyId,
        String position,
        String stack,
        String content,
        Integer compensation
) {
}
