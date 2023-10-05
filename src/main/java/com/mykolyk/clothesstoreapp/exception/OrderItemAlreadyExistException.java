package com.mykolyk.clothesstoreapp.exception;

import com.mykolyk.clothesstoreapp.model.enums.ErrorType;

public class OrderItemAlreadyExistException extends  ServiceException {
    private static final String DEFAULT_MESSAGE = "Order item already exists!";

    public OrderItemAlreadyExistException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }
}
