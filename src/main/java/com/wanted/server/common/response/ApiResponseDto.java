package com.wanted.server.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public record ApiResponseDto<T>(
        int code,
        String message,
        @JsonInclude(value = JsonInclude.Include.NON_NULL)
        T data
) {

    public static <T> ApiResponseDto<T> of(StatusCode code) {
        return new ApiResponseDto<>(code.getHttpStatus().value(), code.getMessage(), null);
    }

    public static <T> ApiResponseDto<T> of(StatusCode code, T data) {
        return new ApiResponseDto<>(code.getHttpStatus().value(), code.getMessage(), data);
    }
}
