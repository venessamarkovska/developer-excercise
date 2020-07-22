package com.example.grocerystore.web;

import antlr.StringUtils;
import com.example.grocerystore.exception.EntityAlreadyExistsException;
import com.example.grocerystore.exception.InvalidRequestException;
import com.example.grocerystore.exception.NonexistingEntityException;
import com.example.grocerystore.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleNonExistingEntityException(NonexistingEntityException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleInvalidRequestException(InvalidRequestException ex) {
        log.error(ex.getMessage(), ex);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("X-Custom-Header", "custom_value_1");
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()),
                headers,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleEntityAlreadyExistsException(EntityAlreadyExistsException ex) {
        log.error(ex.getMessage(), ex);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("X-Custom-Header", "custom_value_1");
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()),
                headers,
                HttpStatus.BAD_REQUEST
        );
    }

}