package com.wanted.server.domain.recruit;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Recruit {

    private Long id;
    private String position;
    private String stack;
    private String content;
    private Integer compensation;
    private LocalDateTime createdAt;
    private Long companyId;

    @Builder(access = AccessLevel.PRIVATE)
    private Recruit(String position, String stack, String content, Integer compensation, Long companyId) {
        this.position = position;
        this.stack = stack;
        this.content = content;
        this.compensation = compensation;
        this.companyId = companyId;
    }

    public static Recruit create(String position, String stack, String content, Integer compensation, Long companyId) {
        return Recruit.builder()
                .position(position)
                .stack(stack)
                .content(content)
                .compensation(compensation)
                .companyId(companyId)
                .build();
    }
}
