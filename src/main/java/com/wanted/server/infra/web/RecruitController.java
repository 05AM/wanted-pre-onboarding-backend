package com.wanted.server.infra.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wanted.server.application.service.RecruitCreateService;
import com.wanted.server.application.service.command.RecruitCreateCommand;
import com.wanted.server.common.response.ApiResponseDto;
import com.wanted.server.common.response.StatusCode;
import com.wanted.server.infra.web.dto.request.RecruitCreateRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "채용공고")
@RestController
@RequestMapping("/api/v1/recruits")
@RequiredArgsConstructor
public class RecruitController {

    private final RecruitCreateService recruitCreateService;

    @Operation(summary = "채용공고 생성")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "생성 성공", content = @Content),
            @ApiResponse(responseCode = "400", description = "유효하지 않은 요청", content = @Content),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 회사", content = @Content)})
    @PostMapping
    public ResponseEntity<ApiResponseDto<Void>> create(
            @Valid @RequestBody RecruitCreateRequest request
    ) {
        RecruitCreateCommand command = RecruitCreateCommand.builder()
                .position(request.position())
                .stack(request.stack())
                .content(request.content())
                .compensation(request.compensation())
                .companyId(request.companyId())
                .build();

        recruitCreateService.create(command);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseDto.of(StatusCode.CREATE_SUCCESS));
    }
}
