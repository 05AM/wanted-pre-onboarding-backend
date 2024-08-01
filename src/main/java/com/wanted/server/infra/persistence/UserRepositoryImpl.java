package com.wanted.server.infra.persistence;

import org.springframework.stereotype.Repository;

import com.wanted.server.domain.repository.UserRepository;
import com.wanted.server.infra.persistence.jpa.repository.JpaUserRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public boolean existById(Long id) {
        return jpaUserRepository.existsById(id);
    }
}
