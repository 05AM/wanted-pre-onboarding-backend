package com.wanted.server.stub;

import com.wanted.server.domain.user.Member;

public class MemberStub {

    public static Member getDefaultMember() {
        return Member.builder()
                .id(1L)
                .name("이찬미")
                .build();
    }
}
