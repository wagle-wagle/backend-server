package com.project.waglewagle.global.error.exception;

import com.project.waglewagle.global.error.ErrorCode;

public class DuplicateMemberException extends BusinessException{
    public DuplicateMemberException(ErrorCode errorCode) {
        super(errorCode);
    }
}