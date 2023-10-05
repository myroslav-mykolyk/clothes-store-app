package com.mykolyk.clothesstoreapp.exception;

import com.mykolyk.clothesstoreapp.model.enums.ErrorType;

public class OrderAlreadyExistException extends  ServiceException {
    private static final String DEFAULT_MESSAGE = "Order already exists!";

    public OrderAlreadyExistException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }
}
