package com.db.db_server.auth.exception;

import com.db.db_server.core.enums.ErrorCode;
import com.db.db_server.core.exception.GlobalException;


public class AuthenticationExceptions {

    public static class UserNotFoundException extends GlobalException {
        public UserNotFoundException() {
            super(ErrorCode.USER_NOT_FOUND);
        }
    }

    public static class UserPasswordMismatchException extends GlobalException {
        public UserPasswordMismatchException() {
            super(ErrorCode.USER_PASSWORD_MISMATCH);
        }
    }
    public static class TokenExpiredException extends GlobalException {
        public TokenExpiredException() {
            super(ErrorCode.TOKEN_EXPIRED);
        }
    }

    public static class UserNotActiveException extends GlobalException {
        public UserNotActiveException() {
            super(ErrorCode.USER_NOT_ACTIVE);
        }
    }

    // 이메일 중복
    public static class UserEmailDuplicateException extends GlobalException {
        public UserEmailDuplicateException() {
            super(ErrorCode.USER_EMAIL_DUPLICATE);
        }
    }
    // 이메일 형식
    public static class UserEmailFormatException extends GlobalException {
        public UserEmailFormatException() {
            super(ErrorCode.USER_EMAIL_FORMAT_INVALID);
        }
    }
    // 비밀번호 형식
    public static class UserPasswordFormatException extends GlobalException {
        public UserPasswordFormatException() {
            super(ErrorCode.USER_PASSWORD_FORMAT_INVALID);
        }
    }
}



