package com.wanted.server.infra.persistence.mapper;

import org.springframework.stereotype.Component;

import com.wanted.server.domain.user.Member;
import com.wanted.server.infra.persistence.jpa.entity.MemberEntity;

@Component
public class MemberPersistenceMapper {

    public MemberEntity toEntity(Member domain) {
        return MemberEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .build();
    }
}
