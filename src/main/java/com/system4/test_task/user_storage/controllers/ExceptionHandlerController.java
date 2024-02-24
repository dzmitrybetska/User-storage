package com.system4.test_task.user_storage.controllers;

import com.system4.test_task.user_storage.dto.ErrorResponse;
import com.system4.test_task.user_storage.exceptions.JsonConversionException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

import static com.system4.test_task.user_storage.utils.Constants.*;
import static java.lang.String.format;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintValidationException(ConstraintViolationException exception) {
        log.warn(EXCEPTION, exception.getMessage());
        return ResponseEntity.badRequest().body(buildErrorResponse(exception));
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception) {
        log.warn(EXCEPTION, exception.getMessage());
        return ResponseEntity.badRequest().body(buildErrorResponse(exception));
    }

    @ExceptionHandler(JsonConversionException.class)
    public ResponseEntity<ErrorResponse> handleJsonMappingException(JsonConversionException exception) {
        log.warn(EXCEPTION, exception.getMessage());
        return ResponseEntity.badRequest().body(buildErrorResponse(exception));
    }

    private ErrorResponse buildErrorResponse(ConstraintViolationException exception) {
        return ErrorResponse.builder()
                .errorCount(exception.getConstraintViolations().size())
                .time(LocalDateTime.now())
                .message(VALIDATION_MESSAGE)
                .build();
    }

    private ErrorResponse buildErrorResponse(SQLIntegrityConstraintViolationException exception) {
        return ErrorResponse.builder()
                .time(LocalDateTime.now())
                .message(format(SQL_VALIDATION_MESSAGE, exception.getMessage()))
                .build();
    }

    private ErrorResponse buildErrorResponse(JsonConversionException exception) {
        return ErrorResponse.builder()
                .time(LocalDateTime.now())
                .message(exception.getMessage())
                .build();
    }
}