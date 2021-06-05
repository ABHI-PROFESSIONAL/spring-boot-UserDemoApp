package com.example.demo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
@Slf4j
public class CustomizedExceptionResponse
        extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
        log.info("=== inside handleAllException ===");
       ExceptionResponce responce=
               new ExceptionResponce(new Date(), ex.getMessage(), request.getDescription(false));

       return new ResponseEntity(responce, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) throws Exception {
        log.info("=== inside handleUserNotFoundException ===");
        ExceptionResponce responce=
                new ExceptionResponce(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(responce, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.info("=== inside handleMethodArgumentNotValid ===");
        ExceptionResponce responce=
                new ExceptionResponce(new Date(), ex.getLocalizedMessage(), ex.getBindingResult().toString());

        return new ResponseEntity(responce, HttpStatus.BAD_REQUEST);

    }

}
