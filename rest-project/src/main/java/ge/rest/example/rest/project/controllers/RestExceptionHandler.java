/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ge.rest.example.rest.project.controllers;

import javax.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author vako
 */
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
            ErrorModel error = new ErrorModel(HttpStatus.BAD_REQUEST, "Validation Error", ex.getBindingResult().toString());

            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<ErrorModel> handleEntityNotFound(EntityNotFoundException ex){
        ErrorModel error = new ErrorModel(HttpStatus.NOT_FOUND, "Entity not found", ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
