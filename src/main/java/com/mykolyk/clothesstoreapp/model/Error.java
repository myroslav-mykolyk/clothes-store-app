package com.mykolyk.clothesstoreapp.model;

import com.mykolyk.clothesstoreapp.model.enums.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Error {
    private String message;
    private ErrorType errorType;
    private LocalDateTime timeStamp;
}
