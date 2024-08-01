package com.wanted.server.infra.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wanted.server.application.service.RecruitCreateService;
import com.wanted.server.application.service.command.RecruitCreateCommand;
import com.wanted.server.common.response.ApiResponse;
import com.wanted.server.common.response.StatusCode;
import com.wanted.server.infra.web.dto.request.RecruitCreateRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/recruits")
@RequiredArgsConstructor
public class RecruitController {

    private final RecruitCreateService recruitCreateService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(
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
                .body(ApiResponse.of(StatusCode.CREATE_SUCCESS));
    }
}
