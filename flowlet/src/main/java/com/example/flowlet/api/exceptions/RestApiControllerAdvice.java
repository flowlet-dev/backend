package com.example.flowlet.api.exceptions;

import com.example.flowlet.api.dtos.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestApiControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleException(Exception e) {
        return ResponseEntity.status(500).body(new ErrorDto("msg.error.internal_server_error.code", "msg.error.internal_server_error.message"));
    }


}
