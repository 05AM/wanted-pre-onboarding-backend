package com.wanted.server.infra.persistence.mapper;

import org.springframework.stereotype.Component;

import com.wanted.server.domain.user.User;
import com.wanted.server.infra.persistence.jpa.entity.UserEntity;

@Component
public class UserPersistenceMapper {

    public UserEntity toEntity(User domain) {
        return UserEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .build();
    }
}
