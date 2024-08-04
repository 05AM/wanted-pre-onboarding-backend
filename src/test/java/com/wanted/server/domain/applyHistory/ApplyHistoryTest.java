package com.wanted.server.domain.applyHistory;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.wanted.server.stub.ApplyHistoryStub;
import com.wanted.server.template.UnitTest;

class ApplyHistoryTest extends UnitTest {

    @DisplayName("지원내역 생성 테스트")
    @Nested
    class Create {

        @DisplayName("지원내역 생성에 성공한다.")
        @Test
        void success() {
            ApplyHistory want = ApplyHistoryStub.getApplyHistory();
            
            ApplyHistory got = ApplyHistory.create(1L, 1L);

            assertThat(got.getMember().getId())
                    .isEqualTo(want.getMember().getId());
            assertThat(got.getRecruit().getId())
                    .isEqualTo(want.getRecruit().getId());
        }
    }
}