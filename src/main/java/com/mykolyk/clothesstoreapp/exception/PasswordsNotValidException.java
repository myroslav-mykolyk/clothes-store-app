package com.mykolyk.clothesstoreapp.exception;

import com.mykolyk.clothesstoreapp.model.enums.ErrorType;

public class PasswordsNotValidException extends ServiceException {
    private static final String DEFAULT_MESSAGE = "Passwords did pass validation. Make sure they are not empty & equal.";

    public PasswordsNotValidException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }
}
