package com.xserver.config;

import com.xserver.common.Response;
import com.xserver.exception.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import static com.xserver.common.ErrorCodeConstant.*;

/**
 */
@ControllerAdvice
public class XserverExceptionHandler {
    public final Logger logger = LoggerFactory.getLogger(XserverExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleException(Exception ex, WebRequest request) {
        Response<String> error = new Response<>();
        error.setStatus(0);
        if (ex instanceof UnauthorizedException) {
            UnauthorizedException unauthorizedException = (UnauthorizedException) ex;
            error.setErrorMessage(unauthorizedException.getMessage());
            error.setErrCode(unauthorizedException.getErrorCode());
        } else if (ex instanceof org.apache.shiro.authz.AuthorizationException) {
            error.setErrorMessage("Authorization false! " + ex.getMessage());
            error.setErrCode(EC_COMMON_UNAUTH);
        } else if (ex instanceof HttpRequestMethodNotSupportedException) {
            error.setErrorMessage(ex.getMessage());
            error.setErrCode(EC_COMMON_REQ_METHOD_ERROR);
        } else {
            error.setErrorMessage("Server inner failed!! ");
            error.setErrCode(EC_COMMON_UNKNOWN_ERROR);
            logger.error("XserverExceptionHandler : ", ex);
        }

        return new ResponseEntity<Object>(error, HttpStatus.OK);
    }
}
