package com.wanted.server.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public record ApiResponse<T>(
        int code,
        String message,
        @JsonInclude(value = JsonInclude.Include.NON_NULL)
        T data
) {

    public static <T> ApiResponse<T> of(StatusCode code) {
        return new ApiResponse<>(code.getHttpStatus().value(), code.getMessage(), null);
    }

    public static <T> ApiResponse<T> of(StatusCode code, T data) {
        return new ApiResponse<>(code.getHttpStatus().value(), code.getMessage(), data);
    }
}
