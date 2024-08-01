package com.wanted.server.domain.recruit;

import java.time.LocalDateTime;

import com.wanted.server.common.exception.model.ValidationException;
import com.wanted.server.common.response.StatusCode;
import com.wanted.server.domain.company.Company;

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
    private Company company;

    @Builder
    private Recruit(Long id, String position, String stack, String content, Integer compensation, Company company) {
        validateCompensationMin(compensation);

        this.id = id;
        this.position = position;
        this.stack = stack;
        this.content = content;
        this.compensation = compensation;
        this.company = company;
    }

    public static Recruit create(String position, String stack, String content, Integer compensation, Long companyId) {
        return Recruit.builder()
                .position(position)
                .stack(stack)
                .content(content)
                .compensation(compensation)
                .company(Company.builder()
                        .id(companyId)
                        .build())
                .build();
    }

    public Recruit update(String position, String stack, String content, Integer compensation) {
        return Recruit.builder()
                .id(this.id)
                .position(position)
                .stack(stack)
                .content(content)
                .compensation(compensation)
                .company(Company.builder()
                        .id(this.company.getId())
                        .build())
                .build();
    }

    private void validateCompensationMin(Integer compensation) {
        if (compensation < 0) {
            throw new ValidationException(StatusCode.INVALID_COMPENSATION_ERROR);
        }
    }
}
