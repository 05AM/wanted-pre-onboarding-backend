package com.wanted.server.common.exception.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.wanted.server.common.exception.model.BusinessException;
import com.wanted.server.common.response.ApiResponseDto;
import com.wanted.server.common.response.StatusCode;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String ERROR_LOG_MESSAGE = "[ERROR] {} : {}";
    private static final String WARN_LOG_MESSAGE = "[WARN] {} : {}";

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ApiResponseDto<Void>> handleBusinessException(final BusinessException e) {
        log.warn(WARN_LOG_MESSAGE, e.getClass().getSimpleName(), e.getMessage());
        e.printStackTrace();

        StatusCode statusCode = e.getStatusCode();

        return ResponseEntity.status(statusCode.getHttpStatus())
                .body(ApiResponseDto.of(statusCode));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<ApiResponseDto<Void>> handleException(final Exception e) {
        log.error(ERROR_LOG_MESSAGE, e.getClass().getSimpleName(), e.getMessage());
        e.printStackTrace();

        return ResponseEntity.internalServerError()
                .body(ApiResponseDto.of(StatusCode.INTERNAL_SERVER_ERROR));
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponseDto<Map<String, String>>> handleConstraintViolationException(ConstraintViolationException e) {
        Map<String, String> errors = new HashMap<>();

        e.getConstraintViolations().forEach(violation -> {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            errors.put(propertyPath, message);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponseDto.of(StatusCode.BAD_REQUEST_ERROR, errors));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponseDto<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponseDto.of(StatusCode.BAD_REQUEST_ERROR, errors));
    }
}
