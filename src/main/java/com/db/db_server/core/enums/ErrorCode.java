package com.db.db_server.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_NOT_FOUND", "해당 사용자를 찾을 수 없습니다."),
    USER_PASSWORD_MISMATCH(HttpStatus.UNAUTHORIZED, "USER_PASSWORD_MISMATCH", "비밀번호가 일치하지 않습니다."),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "TOKEN_EXPIRED", "토큰이 만료되었습니다."),
    USER_NOT_ACTIVE(HttpStatus.UNAUTHORIZED, "USER_NOT_ACTIVE", "사용자가 비활성화 상태입니다."),
    USER_EMAIL_DUPLICATE(HttpStatus.BAD_REQUEST, "USER_EMAIL_DUPLICATE", "이미 사용중인 이메일입니다."),
    USER_EMAIL_FORMAT_INVALID(HttpStatus.BAD_REQUEST, "USER_PASSWORD_INVALID", "이메일 형식이 올바르지 않습니다."),
    USER_PASSWORD_FORMAT_INVALID(HttpStatus.BAD_REQUEST, "USER_PASSWORD_INVALID", "비밀번호 형식이 올바르지 않습니다.");

    // httpStatus - HTTP 상태 코드
    // errorCode - 에러 코드
    // errorMessage - 에러 메시지
    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;
}
