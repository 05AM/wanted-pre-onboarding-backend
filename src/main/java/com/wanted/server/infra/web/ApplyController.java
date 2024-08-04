package com.wanted.server.infra.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wanted.server.application.service.ApplyService;
import com.wanted.server.application.service.command.ApplyHistoryCreateCommand;
import com.wanted.server.common.response.ApiResponseDto;
import com.wanted.server.common.response.StatusCode;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@Tag(name = "지원내역")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ApplyController {

    private final ApplyService applyService;

    @Operation(summary = "채용공고 지원")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "생성 성공", content = @Content),
            @ApiResponse(responseCode = "400", description = "유효하지 않은 요청", content = @Content),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 유저", content = @Content),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 채용공고", content = @Content),
            @ApiResponse(responseCode = "409", description = "이미 해당 공고에 지원한 경우", content = @Content)})
    @PostMapping("/recruits/{recruitId}/apply")
    public ResponseEntity<ApiResponseDto<Void>> apply(
            @Parameter @PathVariable(name = "recruitId") @NotNull Long recruitId
    ) {
        ApplyHistoryCreateCommand command = ApplyHistoryCreateCommand.builder()
                .memberId(1L)
                .recruitId(recruitId)
                .build();

        applyService.apply(command);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseDto.of(StatusCode.CREATE_SUCCESS));
    }
}
