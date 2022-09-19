package com.tmax.cm.superstore.common.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.error.exception.BusinessException;

@RestControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public ResponseDto<String> responseRuntimeException(RuntimeException exception) {
        
        return new ResponseDto<String>(ResponseCode.ERROR_COMMON_RUNTIME, exception.getMessage());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseDto<List<String>> responseMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {

        List<String> errorMessages = exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map((ObjectError objectError) -> {
                    FieldError error = (FieldError) objectError;

                    return "'" + error.getField() + "' " + error.getDefaultMessage();
                })
                .collect(Collectors.toList());

        return new ResponseDto<List<String>>(ResponseCode.ERROR_INVALID_FIELD, errorMessages);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessException.class)
    public ResponseDto<Void> responseRuntimeException(BusinessException exception) {

        return new ResponseDto<Void>(exception.getResponseCode(), null);
    }
}
