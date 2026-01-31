package com.example.flowlet.api.exceptions;

import com.example.flowlet.api.dtos.ErrorDto;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@RestControllerAdvice
@AllArgsConstructor
public class RestApiControllerAdvice extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Override
    protected @Nullable ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode statusCode,
            @NonNull WebRequest request
    ) {
        String code = messageSource.getMessage("error.bad_request.code", null, null);
        String message = Objects.requireNonNull(ex.getBindingResult()
                        .getFieldError())
                .getDefaultMessage();
        return ResponseEntity.badRequest().body(new ErrorDto(code, message));
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            @NonNull Exception ex,
            @Nullable Object body,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode statusCode,
            @NonNull WebRequest request) {

        if (body != null) {
            return super.handleExceptionInternal(ex, body, headers, statusCode, request);
        }

        String code = messageSource.getMessage("error.internal_server_error.code", null, null);
        String message = messageSource.getMessage("error.internal_server_error.message", null, null);

        return super.handleExceptionInternal(ex, new ErrorDto(code, message), headers, statusCode, request);

    }

}
