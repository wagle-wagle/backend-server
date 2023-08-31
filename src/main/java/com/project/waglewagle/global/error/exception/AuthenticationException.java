package com.project.waglewagle.global.error.exception;

import com.project.waglewagle.global.error.ErrorCode;

public class AuthenticationException extends BusinessException{
    public AuthenticationException(ErrorCode errorCode) {
        super(errorCode);
    }
}