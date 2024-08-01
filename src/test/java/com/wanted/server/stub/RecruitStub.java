package com.wanted.server.stub;

import com.wanted.server.application.service.command.RecruitCreateCommand;
import com.wanted.server.application.service.command.RecruitUpdateCommand;
import com.wanted.server.domain.recruit.Recruit;

public class RecruitStub {

    public static Recruit getRecruit() {
        RecruitCreateCommand command = getRecruitCreateCommand();

        return Recruit.create(
                command.position(),
                command.stack(),
                command.content(),
                command.compensation(),
                command.companyId()
        );
    }

    public static RecruitCreateCommand getRecruitCreateCommand() {
        return RecruitCreateCommand.builder()
                .position("백엔드 개발자")
                .stack("Spring Boot")
                .content("백엔드 개발자를 모집합니다. 팀에 합류하여 함께 성장할 인재를 찾고 있습니다.")
                .compensation(500000)
                .companyId(1L)
                .build();
    }

    public static RecruitUpdateCommand getRecruitUpdateCommand() {
        return RecruitUpdateCommand.builder()
                .id(1L)
                .position("디자이너")
                .stack("figma")
                .content("디자이너를 모집합니다. 팀에 합류하여 함께 성장할 인재를 찾고 있습니다.")
                .compensation(1000000)
                .build();
    }
}
