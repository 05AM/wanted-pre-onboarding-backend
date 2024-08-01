package com.wanted.server.application.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.wanted.server.application.service.command.RecruitCreateCommand;
import com.wanted.server.common.exception.model.NotExistException;
import com.wanted.server.common.response.StatusCode;
import com.wanted.server.domain.repository.RecruitRepository;
import com.wanted.server.stub.RecruitStub;
import com.wanted.server.template.UnitTest;

class RecruitCreateServiceTest extends UnitTest {

    @InjectMocks
    private RecruitCreateService recruitCreateService;

    @Mock
    private CompanyValidationService companyValidationService;

    @Mock
    private RecruitRepository recruitRepository;

    @DisplayName("채용공고 생성 서비스 테스트")
    @Nested
    class Create {

        @DisplayName("[성공] 채용공고 생성에 성공한다.")
        @Test
        void success() {
            RecruitCreateCommand command = RecruitStub.getRecruitCreateCommand();
            recruitCreateService.create(command);

            then(companyValidationService).should().validateCompanyExist(command.companyId());
            then(recruitRepository).should().save(any());
        }

        @DisplayName("[실패] 요청한 회사가 존재하지 않으면 채용공고 생성에 실패한다.")
        @Test
        void fail_create_when_company_not_found() {
            RecruitCreateCommand command = RecruitStub.getRecruitCreateCommand();

            willThrow(new NotExistException(StatusCode.COMPANY_NOT_FOUND_ERROR))
                    .given(companyValidationService).validateCompanyExist(anyLong());


            assertThatThrownBy(
                    () -> recruitCreateService.create(command)
            ).isInstanceOf(NotExistException.class).hasMessage(StatusCode.COMPANY_NOT_FOUND_ERROR.getMessage());
        }
    }
}