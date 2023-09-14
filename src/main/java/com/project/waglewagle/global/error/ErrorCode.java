package com.project.waglewagle.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // 회원
    ALREADY_REGISTERED_MEMBER(HttpStatus.BAD_REQUEST, "M0001", "이미 가입된 회원 입니다."),
    MEMBER_NOT_EXIST(HttpStatus.BAD_REQUEST, "M0002", "해당 회원은 존재하지 않습니다. "),

    // 인증•인가
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "A0001", "토큰이 만료되었습니다."),
    NOT_VALID_TOKEN(HttpStatus.UNAUTHORIZED, "A0002", "유효하지 않는 토큰입니다."),

    // 기와(방명록)
    BROAD_NOT_EXIST(HttpStatus.BAD_REQUEST,"B0001", "기와가 존재하지 않습니다."),



    ;

    private HttpStatus httpStatus;
    private String errorCode;
    private String message;

    ErrorCode(HttpStatus httpStatus, String errorCode, String message) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
    }
}