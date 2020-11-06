package com.thoughtworks.capability.gtb.restfulapidesign.common.handler;

import com.thoughtworks.capability.gtb.restfulapidesign.common.errors.ErrorResult;
import com.thoughtworks.capability.gtb.restfulapidesign.common.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * Created by wudibin
 * 2020/11/5
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResult> handle(BadRequestException ex) {
        ErrorResult errorResult = ErrorResult.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(ex.getErrorCode().getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResult> handle(MethodArgumentNotValidException ex) {
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        final int[] count = {0};
        StringBuilder message = new StringBuilder();
        allErrors.forEach(e -> {
            if (count[0] == allErrors.size() - 1) {
                message.append(e.getDefaultMessage());
            } else {
                message.append(e.getDefaultMessage()).append(" & ");
                count[0]++;
            }
        });
        ErrorResult errorResult = ErrorResult.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(message.toString())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResult> handle(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        final int[] count = {0};
        StringBuilder message = new StringBuilder();
        violations.forEach(c -> {
            if (count[0] == violations.size() - 1) {
                message.append(c.getMessage());
            } else {
                message.append(c.getMessage()).append(" & ");
                count[0]++;
            }
        });
        ErrorResult errorResult = ErrorResult.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(message.toString())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }

}
