package com.wanted.server.infra.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wanted.server.infra.persistence.jpa.entity.UserEntity;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
}
