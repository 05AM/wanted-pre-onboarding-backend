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

class RecruitDeleteServiceTest extends UnitTest {

    @InjectMocks
    private RecruitDeleteService recruitDeleteService;

    @Mock
    private RecruitRepository recruitRepository;

    @DisplayName("채용공고 삭제 서비스 테스트")
    @Nested
    class Create {

        @DisplayName("[성공] 채용공고 삭제에 성공한다.")
        @Test
        void success() {
            recruitDeleteService.delete(any());

            then(recruitRepository).should().delete(any());
        }

        @DisplayName("[실패] 요청한 채용공고가 존재하지 않으면 채용공고 삭제에 실패한다.")
        @Test
        void fail_delete_when_recruit_not_found() {
            willThrow(new NotExistException(StatusCode.RECRUIT_NOT_FOUND_ERROR))
                    .given(recruitRepository).findById(anyLong());

            assertThatThrownBy(
                    () -> recruitDeleteService.delete(anyLong())
            ).isInstanceOf(NotExistException.class).hasMessage(StatusCode.RECRUIT_NOT_FOUND_ERROR.getMessage());
        }
    }
}