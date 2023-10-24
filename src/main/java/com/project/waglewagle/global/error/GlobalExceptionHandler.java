package com.project.waglewagle.global.error;

import com.project.waglewagle.global.error.exception.DuplicateMemberException;
import com.project.waglewagle.global.error.exception.EntityNotFoundException;
import com.project.waglewagle.global.util.CommonResponse;
import com.project.waglewagle.global.error.exception.BusinessException;
import com.project.waglewagle.global.util.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(DuplicateMemberException.class)
    public CommonResponse handleDuplicateMemberException(DuplicateMemberException e){
        log.error("DuplicateMemberException ",e);
        return ApiResponse.createBusinessFail(e.getErrorCode().getHttpStatus(),e.getErrorCode());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public CommonResponse handleEntityNotFoundException(EntityNotFoundException e){
        log.error("EntityNotFoundException ",e);
        return ApiResponse.createBusinessFail(e.getErrorCode().getHttpStatus(),e.getErrorCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResponse methodValidException(MethodArgumentNotValidException e){
        log.error("MethodArgumentNotValidException ",e);
        return ApiResponse.createFail(HttpStatus.BAD_REQUEST,"양식을 맞춰주세요.");
    }

}
