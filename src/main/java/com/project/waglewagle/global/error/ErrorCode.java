package com.project.waglewagle.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // 회원
    ALREADY_REGISTERED_MEMBER(HttpStatus.BAD_REQUEST, "M0001", "이미 가입된 회원 입니다."),
    MEMBER_NOT_EXIST(HttpStatus.BAD_REQUEST, "M0002", "해당 회원은 존재하지 않습니다. "),
    NOT_MATCHED_PASSWORD(HttpStatus.BAD_REQUEST, "M003", "비밀번호가 일치하지 않습니다. "),


    // 인증•인가
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "A0001", "토큰이 만료되었습니다."),
    NOT_VALID_TOKEN(HttpStatus.UNAUTHORIZED, "A0002", "유효하지 않는 토큰입니다."),
    NOT_EXISTS_AUTHORIZATION(HttpStatus.UNAUTHORIZED, "A-003", "Authorization Header가 빈값 입니다."),
    NOT_VALID_BEARER_GRANT_TYPE(HttpStatus.UNAUTHORIZED, "A-004", "인증 타입이 Bearer 타입이 아닙니다."),
    NOT_ACCESS_AUTHORITY(HttpStatus.UNAUTHORIZED, "A-005", "접근 권한이 없습니다."),
    FORBIDDEN_ADMIN(HttpStatus.UNAUTHORIZED, "A-006", "관리자 Role이 아닙니다."),


    // 기와집(게시판)
    BROAD_NOT_EXIST(HttpStatus.BAD_REQUEST,"B0001", "기와집이 존재하지 않습니다."),
    ALREADY_MAKED_BROAD(HttpStatus.BAD_REQUEST,"B0002","이미 기와집이 있는 회원입니다."),


    // 기와(방명록)
    POST_NOT_EXIST(HttpStatus.BAD_REQUEST, "P0001", "기와가 존재하지 않습니다."),

    // 유효성검사
    REQUEST_NOT_VALID(HttpStatus.BAD_REQUEST, "R0001", "잘못된 REQUEST 형식입니다."),


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