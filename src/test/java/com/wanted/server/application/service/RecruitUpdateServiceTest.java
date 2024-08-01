package com.wanted.server.application.service;

import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.wanted.server.application.service.command.RecruitUpdateCommand;
import com.wanted.server.domain.repository.RecruitRepository;
import com.wanted.server.stub.RecruitStub;
import com.wanted.server.template.UnitTest;

class RecruitUpdateServiceTest extends UnitTest {

    @InjectMocks
    private RecruitUpdateService recruitUpdateService;

    @Mock
    private RecruitRepository recruitRepository;

    @DisplayName("채용공고 수정 서비스 테스트")
    @Nested
    class Update {

        @DisplayName("[성공] 채용공고 수정에 성공한다.")
        @Test
        void success() {
            RecruitUpdateCommand command = RecruitStub.getRecruitUpdateCommand();
            recruitUpdateService.update(command);

            then(recruitRepository).should().save(any());
        }
    }
}