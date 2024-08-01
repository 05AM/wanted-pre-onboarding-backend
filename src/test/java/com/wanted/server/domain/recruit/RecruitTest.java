package com.wanted.server.domain.recruit;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.wanted.server.application.service.command.RecruitCreateCommand;
import com.wanted.server.stub.RecruitStub;
import com.wanted.server.template.UnitTest;

class RecruitTest extends UnitTest {

    @DisplayName("채용공고 도메인 테스트")
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
            assertThat(recruit.getCompanyId()).isEqualTo(command.companyId());
        }
    }
}