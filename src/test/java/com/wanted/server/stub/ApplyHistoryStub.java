package com.wanted.server.stub;

import com.wanted.server.application.service.command.ApplyHistoryCreateCommand;
import com.wanted.server.domain.applyHistory.ApplyHistory;

public class ApplyHistoryStub {

    public static ApplyHistoryCreateCommand getApplyHistoryCreateCommand() {
        return ApplyHistoryCreateCommand.builder()
                .memberId(MemberStub.getDefaultMember().getId())
                .recruitId(RecruitStub.getRecruit().getId())
                .build();
    }

    public static ApplyHistory getApplyHistory() {
        return ApplyHistory.builder()
                .id(1L)
                .member(MemberStub.getDefaultMember())
                .recruit(RecruitStub.getRecruit())
                .build();
    }
}
