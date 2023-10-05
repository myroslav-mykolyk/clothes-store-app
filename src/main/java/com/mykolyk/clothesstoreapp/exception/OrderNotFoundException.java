package com.mykolyk.clothesstoreapp.exception;

import com.mykolyk.clothesstoreapp.model.enums.ErrorType;

public class OrderNotFoundException extends ServiceException {
    private static final String DEFAULT_MESSAGE = "Order is not found!";

    public OrderNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }
}
