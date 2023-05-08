package com.blog_assessment.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.Optional;

@ControllerAdvice
public class GlobalExceptionHandler {

//    handle specific exceptions
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleEntityNotFoundException
        (final EntityNotFoundException exception){
        return exception(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BlogApiException.class)
    public ResponseEntity<ExceptionResponse> handleBlogApiException(final BlogApiException exception){
        return exception(exception, HttpStatus.BAD_REQUEST);
    }
    //    handle global exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGlobalException(final Exception exception){
        return exception(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    private ResponseEntity<ExceptionResponse> exception(final Exception exception,
                                                        final HttpStatus httpStatus) {
        final String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        final ExceptionResponse errorResponse = ExceptionResponse.builder()
                .status(httpStatus.value())
                .error(httpStatus.getReasonPhrase())
                .timestamp(new Date())
                .message(message)
                .build();
        return new ResponseEntity<>(errorResponse, httpStatus);
    }
}
