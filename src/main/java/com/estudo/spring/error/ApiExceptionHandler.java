package com.estudo.spring.error;

import com.estudo.spring.error.domain.ErrorResponse;
import com.estudo.spring.error.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebInputException;

import java.util.Locale;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.status;

@RestControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler {
    private final ApiErrorHandler apiErrorHandler;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(
            final NotFoundException exception, final Locale locale) {
        ResponseEntity<ErrorResponse> error =
                status(NOT_FOUND)
                        .body(
                                apiErrorHandler.getErrorMessage(
                                        locale,
                                        NOT_FOUND.value(),
                                        exception.getMessageKey(),
                                        exception.getNotFoundValue()));
        return error;
    }

    private ResponseEntity<ErrorResponse> getErrorResponse(
            final Locale locale, final String messageKey, final ServerWebInputException exception) {
        ResponseEntity<ErrorResponse> error =
                status(BAD_REQUEST)
                        .body(
                                apiErrorHandler.getErrorMessage(
                                        locale, BAD_REQUEST.value(), messageKey));
        return error;
    }
}
