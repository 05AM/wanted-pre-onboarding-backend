package com.wanted.server.stub;

import com.wanted.server.application.service.command.RecruitCreateCommand;

public class RecruitStub {

    public static RecruitCreateCommand getRecruitCreateCommand() {
        return RecruitCreateCommand.builder()
                .position("백엔드 개발자")
                .stack("Spring Boot")
                .content("백엔드 개발자를 모집합니다. 팀에 합류하여 함께 성장할 인재를 찾고 있습니다.")
                .compensation(500000)
                .companyId(1L)
                .build();
    }
}
