package com.project.waglewagle.global.util;

import com.project.waglewagle.global.error.ErrorCode;
import org.springframework.http.HttpStatus;

public class ApiResponse {

    public static CommonResponse createSuccess(String message, HttpStatus httpStatus, Object data){
        return CommonResponse.builder()
                .status("SUCCESS")
                .message(message)
                .data(data)
                .build();
    }

    public static CommonResponse createFail(HttpStatus httpStatus, String errorMessage){
        return CommonResponse.builder()
                .status("FAIL")
                .error(httpStatus.toString())
                .message(errorMessage)
                .build();
    }

    public static CommonResponse createBusinessFail(HttpStatus httpStatus, ErrorCode errorCode){
        return CommonResponse.builder()
                .status("FAIL")
                .error(httpStatus.toString())
                .message(errorCode.getMessage())
                .build();
    }


}
