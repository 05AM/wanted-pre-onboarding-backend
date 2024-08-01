package com.wanted.server.common.exception.model;

import com.wanted.server.common.response.StatusCode;

public class NotExistException extends BusinessException{

    public NotExistException(StatusCode statusCode) {
        super(statusCode);
    }
}
