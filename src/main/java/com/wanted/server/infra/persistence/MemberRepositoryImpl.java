package com.wanted.server.infra.persistence;

import org.springframework.stereotype.Repository;

import com.wanted.server.domain.repository.MemberRepository;
import com.wanted.server.infra.persistence.jpa.repository.JpaMemberRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final JpaMemberRepository jpaMemberRepository;

    @Override
    public boolean existById(Long id) {
        return jpaMemberRepository.existsById(id);
    }
}
