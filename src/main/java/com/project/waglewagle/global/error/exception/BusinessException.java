package com.project.waglewagle.global.error.exception;

import com.project.waglewagle.global.error.ErrorCode;

// 비즈니스 로직 수행시 발생한 예외처리를 위한 BusinessException
public class BusinessException extends RuntimeException{

    private ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
