package com.wanted.server.application.service;

import org.springframework.stereotype.Service;

import com.wanted.server.common.exception.model.NotExistException;
import com.wanted.server.common.response.StatusCode;
import com.wanted.server.domain.repository.CompanyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyValidationService {

    private final CompanyRepository companyRepository;

    public void validateCompanyExist(Long companyId) {
        if (!companyRepository.isExist(companyId)) {
            throw new NotExistException(StatusCode.COMPANY_NOT_FOUND);
        }
    }
}
