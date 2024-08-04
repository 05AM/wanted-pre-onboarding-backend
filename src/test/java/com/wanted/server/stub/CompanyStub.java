package com.wanted.server.stub;

import com.wanted.server.domain.company.Company;

public class CompanyStub {

    public static Company getCompany() {
        return Company.builder()
                .id(1L)
                .name("네이버")
                .nation("한국")
                .region("서울")
                .build();
    }
}
