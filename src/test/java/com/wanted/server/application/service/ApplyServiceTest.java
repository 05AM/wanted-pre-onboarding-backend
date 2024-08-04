package com.wanted.server.application.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.wanted.server.application.service.command.ApplyHistoryCreateCommand;
import com.wanted.server.common.exception.model.InvalidStateException;
import com.wanted.server.common.exception.model.NotExistException;
import com.wanted.server.common.response.StatusCode;
import com.wanted.server.domain.repository.ApplyHistoryRepository;
import com.wanted.server.stub.ApplyHistoryStub;
import com.wanted.server.template.UnitTest;

class ApplyServiceTest extends UnitTest {

    @InjectMocks
    private ApplyService applyService;

    @Mock
    private ApplyHistoryValidationService applyHistoryValidationService;
    @Mock
    private MemberValidationService memberValidationService;
    @Mock
    private RecruitValidationService recruitValidationService;
    @Mock
    private ApplyHistoryRepository applyHistoryRepository;

    @DisplayName("채용공고 지원 테스트")
    @Nested
    class Apply {

        @DisplayName("[성공] 채용공고 지원에 성공한다.")
        @Test
        void success() {
            ApplyHistoryCreateCommand command = ApplyHistoryStub.getApplyHistoryCreateCommand();

            applyService.apply(command);

            then(memberValidationService).should().validateUserExist(any());
            then(recruitValidationService).should().validateRecruitExist(any());
            then(applyHistoryValidationService).should().validateUserAppliedAlready(anyLong(), anyLong());
            then(applyHistoryRepository).should().save(any());
        }

        @DisplayName("[실패] 존재하지 않는 멤버 정보로 요청한 경우 지원에 실패한다.")
        @Test
        void fail_when_member_not_found() {
            ApplyHistoryCreateCommand command = ApplyHistoryStub.getApplyHistoryCreateCommand();
            willThrow(new NotExistException(StatusCode.USER_NOT_FOUND_ERROR))
                    .given(memberValidationService).validateUserExist(command.memberId());

            assertThatThrownBy(
                    () -> applyService.apply(command)
            ).isInstanceOf(NotExistException.class)
                    .hasMessage(StatusCode.USER_NOT_FOUND_ERROR.getMessage());
        }

        @DisplayName("[실패] 존재하지 않는 채용공고 정보로 요청한 경우 지원에 실패한다.")
        @Test
        void fail_when_recruit_not_found() {
            ApplyHistoryCreateCommand command = ApplyHistoryStub.getApplyHistoryCreateCommand();
            willThrow(new NotExistException(StatusCode.RECRUIT_NOT_FOUND_ERROR))
                    .given(recruitValidationService).validateRecruitExist(command.recruitId());

            assertThatThrownBy(
                    () -> applyService.apply(command)
            ).isInstanceOf(NotExistException.class)
                    .hasMessage(StatusCode.RECRUIT_NOT_FOUND_ERROR.getMessage());
        }

        @DisplayName("[실패] 이미 지원한 이력이 있는 채용공고의 경우 지원에 실패한다.")
        @Test
        void fail_when_already_applied() {
            ApplyHistoryCreateCommand command = ApplyHistoryStub.getApplyHistoryCreateCommand();
            willThrow(new InvalidStateException(StatusCode.USER_ALREADY_APPLIED_ERROR))
                    .given(applyHistoryValidationService).validateUserAppliedAlready(command.memberId(), command.recruitId());

            assertThatThrownBy(
                    () -> applyService.apply(command)
            ).isInstanceOf(InvalidStateException.class)
                    .hasMessage(StatusCode.USER_ALREADY_APPLIED_ERROR.getMessage());
        }
    }
}