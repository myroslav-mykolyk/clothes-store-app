package com.mykolyk.clothesstoreapp.controller;

import com.mykolyk.clothesstoreapp.exception.ServiceException;
import com.mykolyk.clothesstoreapp.model.enums.ErrorType;
import com.mykolyk.clothesstoreapp.model.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ErrorHandlingController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<Error> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error("handleMethodArgumentNotValidException: message: {}", exception.getMessage(), exception);
        return exception.getBindingResult().getAllErrors().stream()
                .map(error -> new Error(error.getDefaultMessage(), ErrorType.VALIDATION_ERROR_TYPE, LocalDateTime.now()))
                .collect(Collectors.toList());
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error handleServiceException(ServiceException exception, HandlerMethod handlerMethod) {
        log.error("handleServiceException: message: {}, method: {}", exception.getMessage(), handlerMethod.getMethod().getName(), exception);
        return new Error(exception.getMessage(), exception.getErrorType(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error handleException(Exception exception, HandlerMethod handlerMethod) {
        log.error("handleException: message: {}, method: {}", exception.getMessage(), handlerMethod.getMethod().getName(), exception);
        return new Error(exception.getMessage(), ErrorType.FATAL_ERROR_TYPE, LocalDateTime.now());
    }
}

