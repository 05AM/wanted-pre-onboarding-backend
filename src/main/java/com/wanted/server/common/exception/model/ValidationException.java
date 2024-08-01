package com.wanted.server.common.exception.model;

import com.wanted.server.common.response.StatusCode;

public class ValidationException extends BusinessException {
    public ValidationException(StatusCode statusCode) {
        super(statusCode);
    }
}
