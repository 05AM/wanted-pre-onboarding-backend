package com.wanted.server.common.response;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum StatusCode {

    /**
     * 201
     */
    CREATE_SUCCESS(HttpStatus.CREATED, "리소스가 성공적으로 생성되었습니다."),

    /**
     * 400
     */
    BAD_REQUEST_ERROR(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    INVALID_COMPENSATION_ERROR(HttpStatus.BAD_REQUEST, "보상금 금액은 0보다 작을 수 없습니다."),

    /**
     * 404
     */
    COMPANY_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "요청한 회사 정보를 찾을 수 없습니다."),

    /**
     * 500
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부에서 에러가 발생하였습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
