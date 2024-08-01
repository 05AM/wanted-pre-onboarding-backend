package com.wanted.server.common.exception.model;

import com.wanted.server.common.response.StatusCode;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final StatusCode statusCode;

    public BusinessException(StatusCode statusCode) {
        this.statusCode = statusCode;
    }
}
