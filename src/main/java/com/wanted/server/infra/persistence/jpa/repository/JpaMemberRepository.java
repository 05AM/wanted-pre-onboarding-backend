package com.wanted.server.infra.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wanted.server.infra.persistence.jpa.entity.MemberEntity;

public interface JpaMemberRepository extends JpaRepository<MemberEntity, Long> {
}
