package com.wanted.server.domain.company;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Company {

    private Long id;
    private String name;
    private String nation;
    private String region;
}
