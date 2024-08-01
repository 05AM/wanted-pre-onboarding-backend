package com.wanted.server.infra.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wanted.server.application.ApplyService;
import com.wanted.server.application.service.command.ApplyHistoryCreateCommand;
import com.wanted.server.common.response.ApiResponseDto;
import com.wanted.server.common.response.StatusCode;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ApplyController {

    private final ApplyService applyService;

    @PostMapping("/recruits/{recruitId}/apply")
    public ResponseEntity<ApiResponseDto<Void>> apply(
            @PathVariable @NotNull Long recruitId
    ) {
        ApplyHistoryCreateCommand command = ApplyHistoryCreateCommand.builder()
                .userId(1L)
                .recruitId(recruitId)
                .build();

        applyService.create(command);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseDto.of(StatusCode.CREATE_SUCCESS));
    }
}
