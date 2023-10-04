package com.mykolyk.clothesstoreapp.exception;

import com.mykolyk.clothesstoreapp.model.enums.ErrorType;

public class ProductAlreadyExistException extends ServiceException{
    private static final String DEFAULT_MESSAGE = "Product already exists!";

    public ProductAlreadyExistException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }
}
