package com.db.db_server.core.exception;

import com.db.db_server.core.enums.ErrorCode;
import com.db.db_server.core.response.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice // @ResponseBody 자동 포함 JSON 형태로 바로 변환
public class GlobalExceptionHandler {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(GlobalException ex) {
        ErrorCode errorCode = ex.getErrorCode();

        ErrorResponse errorResponse = new ErrorResponse(
                errorCode.getHttpStatus().value(),
                errorCode.getErrorMessage(),
                errorCode.getErrorCode()
        );

        return new ResponseEntity<>(errorResponse, errorCode.getHttpStatus());
    }


}

