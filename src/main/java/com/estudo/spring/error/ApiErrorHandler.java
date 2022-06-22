package com.estudo.spring.error;

import com.estudo.spring.error.domain.ApiError;
import com.estudo.spring.error.domain.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApiErrorHandler {

    private static final String NO_MESSAGE_AVAILABLE = "No message available";
    private static final String DEFAULT_ERROR_MESSAGE =
            "Couldn't find any message for {} code under {} locale";

    @Qualifier("customMessageSource")
    private final MessageSource applicationErrorMessageSource;

    public ErrorResponse getErrorMessage(
            Locale locale, int httpStatusCode, String messageKey, String... args) {
        return toErrorResponse(
                httpStatusCode, Collections.singletonList(toApiError(messageKey, locale, args)));
    }

    public ErrorResponse getErrorMessage(
            Locale locale, int httpStatusCode, List<FieldError> allErrors) {
        return toErrorResponse(
                httpStatusCode,
                allErrors
                        .stream()
                        .map(
                                objectError ->
                                        toApiError(
                                                (isBlank(objectError.getDefaultMessage()))? "" : objectError.getDefaultMessage(),
                                                locale,
                                                (isBlank(objectError.getField()))? "" : objectError.getField()))
                        .collect(Collectors.toList()));
    }

    private ErrorResponse toErrorResponse(int httpStatusCode, List<ApiError> apiErrors) {
        return ErrorResponse.builder().statusCode(httpStatusCode).errors(apiErrors).build();
    }

    private ApiError toApiError(String messageKey, Locale locale, String... args) {
        String message;
        try {
            message = applicationErrorMessageSource.getMessage(messageKey, args, locale);
        } catch (NoSuchMessageException e) {
            log.error(DEFAULT_ERROR_MESSAGE, messageKey, locale);
            message = NO_MESSAGE_AVAILABLE;
        }
        return new ApiError(messageKey, message, args);
    }
}
