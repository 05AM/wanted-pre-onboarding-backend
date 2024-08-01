package com.wanted.server.domain.applyHistory;

import java.time.LocalDateTime;

import com.wanted.server.domain.recruit.Recruit;
import com.wanted.server.domain.user.Member;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class ApplyHistory {

    private Long id;
    private Member member;
    private Recruit recruit;
    private LocalDateTime createdAt;

    public static ApplyHistory record(Long userId, Long recruitId) {
        return ApplyHistory.builder()
                .member(Member.builder()
                        .id(userId)
                        .build())
                .recruit(Recruit.builder()
                        .id(recruitId)
                        .build())
                .build();
    }
}
