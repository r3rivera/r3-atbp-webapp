package com.r3projects.atbp.exception.handler;

import com.r3projects.atbp.exception.JDBCException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
public class AppsServiceExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({JDBCException.class})
    public ResponseEntity<ErrorResponse> handleJDBCException(HttpServletRequest servletRequest, final JDBCException ex){
        log.error("Handling JDBCException Error!");
        final ErrorResponse response = getErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ServletRequestBindingException.class, MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorResponse> handleBadRequest(HttpServletRequest servletRequest, final Exception ex){
        log.error("Handling Bad Request Error!");
        final ErrorResponse response = getErrorResponse(ex, HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }




    private ErrorResponse getErrorResponse(Throwable ex, final HttpStatus status, final String message){
         final ErrorResponse.Builder err  = ErrorResponse.builder(ex, status, message);
         return err.build();
    }
}
