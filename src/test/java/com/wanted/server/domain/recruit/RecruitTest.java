package com.wanted.server.domain.recruit;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.wanted.server.application.service.command.RecruitCreateCommand;
import com.wanted.server.application.service.command.RecruitUpdateCommand;
import com.wanted.server.common.exception.model.ValidationException;
import com.wanted.server.common.response.StatusCode;
import com.wanted.server.stub.RecruitStub;
import com.wanted.server.template.UnitTest;

class RecruitTest extends UnitTest {

    @DisplayName("채용공고 생성 테스트")
    @Nested
    class Create {

        @DisplayName("[성공] 채용공고 생성에 성공한다.")
        @Test
        void success() {
            RecruitCreateCommand command = RecruitStub.getRecruitCreateCommand();

            Recruit recruit = Recruit.create(
                    command.position(),
                    command.stack(),
                    command.content(),
                    command.compensation(),
                    command.companyId());

            assertThat(recruit).isNotNull();
            assertThat(recruit.getPosition()).isEqualTo(command.position());
            assertThat(recruit.getStack()).isEqualTo(command.stack());
            assertThat(recruit.getContent()).isEqualTo(command.content());
            assertThat(recruit.getCompensation()).isEqualTo(command.compensation());
            assertThat(recruit.getCompany().getId()).isEqualTo(command.companyId());
        }

        @DisplayName("[실패] 합격 보상금이 null이거나 금액이 0보다 작은 경우 채용공고 생성에 실패한다.")
        @ParameterizedTest
        @NullSource
        @ValueSource(ints = { -1, Integer.MIN_VALUE })
        void fail_create_when_compensation_is_null_or_lesser_than_zero(Integer compensation) {
            RecruitCreateCommand command = RecruitStub.getRecruitCreateCommand();

            assertThatThrownBy(() ->
                    Recruit.create(
                            command.position(),
                            command.stack(),
                            command.content(),
                            compensation,
                            command.companyId())
            ).isInstanceOf(ValidationException.class).hasMessage(StatusCode.INVALID_COMPENSATION_ERROR.getMessage());
        }
    }

    @DisplayName("채용공고 수정 테스트")
    @Nested
    class Update {

        @DisplayName("[성공] 채용공고 수정에 성공한다.")
        @Test
        void success() {
            RecruitUpdateCommand command = RecruitStub.getRecruitUpdateCommand();

            Recruit updated = RecruitStub.getRecruit().update(
                    command.position(),
                    command.stack(),
                    command.content(),
                    command.compensation()
            );

            assertThat(updated).isNotNull();
            assertThat(updated.getPosition()).isEqualTo(command.position());
            assertThat(updated.getStack()).isEqualTo(command.stack());
            assertThat(updated.getContent()).isEqualTo(command.content());
            assertThat(updated.getCompensation()).isEqualTo(command.compensation());
        }

        @DisplayName("[실패] 보상금 금액이 0보다 작은 경우 채용공고 수정에 실패한다.")
        @Test
        void fail_update_when_compensation_lesser_than_zero() {
            RecruitUpdateCommand command = RecruitStub.getRecruitUpdateCommand();
            Recruit recruit = RecruitStub.getRecruit();

            assertThatThrownBy(() ->
                    recruit.update(
                            command.position(),
                            command.stack(),
                            command.content(),
                            -1)
            ).isInstanceOf(ValidationException.class).hasMessage(StatusCode.INVALID_COMPENSATION_ERROR.getMessage());
        }

        @DisplayName("[실패] 합격 보상금이 null이거나 금액이 0보다 작은 경우 채용공고 수정에 실패한다.")
        @ParameterizedTest
        @NullSource
        @ValueSource(ints = { -1, Integer.MIN_VALUE })
        void fail_create_when_compensation_is_null_or_lesser_than_zero(Integer compensation) {
            RecruitUpdateCommand command = RecruitStub.getRecruitUpdateCommand();
            Recruit recruit = RecruitStub.getRecruit();

            assertThatThrownBy(() ->
                    recruit.update(
                            command.position(),
                            command.stack(),
                            command.content(),
                            compensation)
            ).isInstanceOf(ValidationException.class).hasMessage(StatusCode.INVALID_COMPENSATION_ERROR.getMessage());
        }
    }
}