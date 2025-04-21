package com.db.db_server.core.exception;

import com.db.db_server.core.enums.ErrorCode;
import lombok.Getter;

@Getter
public abstract class GlobalException extends RuntimeException {
    private final ErrorCode errorCode;

    public GlobalException(ErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
    }
}
