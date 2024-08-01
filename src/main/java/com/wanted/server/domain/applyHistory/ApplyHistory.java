package com.wanted.server.domain.applyHistory;

import java.time.LocalDateTime;

import com.wanted.server.domain.recruit.Recruit;
import com.wanted.server.domain.user.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class ApplyHistory {

    private Long id;
    private User user;
    private Recruit recruit;
    private LocalDateTime createdAt;
}
