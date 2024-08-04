package com.wanted.server.domain.company;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Company {

    private final Long id;
    private final String name;
    private final String nation;
    private final String region;
}
