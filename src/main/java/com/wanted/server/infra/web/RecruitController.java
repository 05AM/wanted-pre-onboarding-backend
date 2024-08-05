package com.wanted.server.infra.web;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wanted.server.application.service.RecruitCreateService;
import com.wanted.server.application.service.RecruitDeleteService;
import com.wanted.server.application.service.RecruitQueryService;
import com.wanted.server.application.service.RecruitUpdateService;
import com.wanted.server.application.service.command.RecruitCreateCommand;
import com.wanted.server.application.service.command.RecruitUpdateCommand;
import com.wanted.server.common.response.ApiResponseDto;
import com.wanted.server.common.response.StatusCode;
import com.wanted.server.infra.web.dto.request.RecruitCreateRequest;
import com.wanted.server.infra.web.dto.request.RecruitUpdateRequest;
import com.wanted.server.infra.web.dto.response.RecruitDetailResponse;
import com.wanted.server.infra.web.dto.response.RecruitSearchResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@Tag(name = "채용공고")
@RestController
@RequestMapping("/api/v1/recruits")
@RequiredArgsConstructor
public class RecruitController {

    private final RecruitQueryService recruitQueryService;
    private final RecruitCreateService recruitCreateService;
    private final RecruitUpdateService recruitUpdateService;
    private final RecruitDeleteService recruitDeleteService;

    @Operation(summary = "채용공고 검색")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content)})
    @GetMapping
    public ResponseEntity<ApiResponseDto<RecruitSearchResult>> search(
        @Parameter(description = "검색어") @RequestParam(name = "search", required = false) String keyword,
        @Parameter(name = "pageable", description = "페이지 정보")
        @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC)
        Pageable pageable
    ) {
        RecruitSearchResult response = recruitQueryService.search(keyword, pageable);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDto.of(StatusCode.GET_SUCCESS, response));
    }

    @Operation(summary = "채용공고 상세 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 채용공고", content = @Content)})
    @GetMapping("/{recruitId}")
    public ResponseEntity<ApiResponseDto<RecruitDetailResponse>> getDetail(
            @Parameter @PathVariable(name = "recruitId") @NotNull Long recruitId
    ) {
        RecruitDetailResponse response = recruitQueryService.getDetail(recruitId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDto.of(StatusCode.GET_SUCCESS, response));
    }

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

    @Operation(summary = "채용공고 수정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "수정 성공", content = @Content),
            @ApiResponse(responseCode = "400", description = "유효하지 않은 요청", content = @Content)})
    @PatchMapping("/{recruitId}")
    public ResponseEntity<ApiResponseDto<Void>> update(
            @Valid @RequestBody RecruitUpdateRequest request,
            @Parameter @PathVariable(name = "recruitId") @NotNull Long recruitId
    ) {
        RecruitUpdateCommand command = RecruitUpdateCommand.builder()
                .id(recruitId)
                .position(request.position())
                .stack(request.stack())
                .content(request.content())
                .compensation(request.compensation())
                .build();

        recruitUpdateService.update(command);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDto.of(StatusCode.UPDATE_SUCCESS));
    }

    @Operation(summary = "채용공고 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "삭제 성공", content = @Content),
            @ApiResponse(responseCode = "400", description = "유효하지 않은 요청", content = @Content),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 채용공고", content = @Content)})
    @DeleteMapping("/{recruitId}")
    public ResponseEntity<ApiResponseDto<Void>> delete(
            @Parameter @PathVariable(name = "recruitId") @NotNull Long recruitId
    ) {
        recruitDeleteService.delete(recruitId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseDto.of(StatusCode.DELETE_SUCCESS));
    }
}
