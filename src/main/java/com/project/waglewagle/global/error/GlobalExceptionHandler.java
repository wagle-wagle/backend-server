package com.project.waglewagle.global.error;

import com.project.waglewagle.dto.common.CommonResponse;
import com.project.waglewagle.global.error.exception.BusinessException;
import com.project.waglewagle.global.util.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public CommonResponse handleBusinessException(BusinessException e){
        log.error("BusinessException ", e);
        ErrorCode errorCode = e.getErrorCode();
        return ApiResponse.createBusinessFail(errorCode.getHttpStatus(), errorCode);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public CommonResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        log.error("HttpRequestMethodNotSupportedException ", e);
        return ApiResponse.createFail(HttpStatus.METHOD_NOT_ALLOWED, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public CommonResponse handleException(Exception e){
        log.error("Exception ", e);
        return ApiResponse.createFail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }


/*    @ExceptionHandler(NoHandlerFoundException.class)
    public CommonResponse handelNoHandlerFoundException(NoHandlerFoundException e){
        log.error("NoHandlerFoundException ", e);
        return ApiResponse.createFail(HttpStatus.NOT_FOUND, e.getMessage());
    }*/


}
