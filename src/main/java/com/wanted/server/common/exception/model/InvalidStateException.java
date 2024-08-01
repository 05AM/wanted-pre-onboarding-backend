package com.wanted.server.common.exception.model;

import com.wanted.server.common.response.StatusCode;

public class InvalidStateException extends BusinessException{

    public InvalidStateException(StatusCode statusCode) {
        super(statusCode);
    }
}
