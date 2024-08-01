package com.wanted.server.application.service.command;

import lombok.Builder;

@Builder
public record ApplyHistoryCreateCommand(
        Long userId,
        Long recruitId
) {
}
