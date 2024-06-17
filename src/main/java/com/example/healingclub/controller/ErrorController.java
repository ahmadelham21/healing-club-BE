package com.example.healingclub.controller;

import com.example.healingclub.dto.response.BaseResponse;
import com.example.healingclub.dto.response.CommonResponse;
import com.example.healingclub.dto.response.CommonResponseWithoutData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity<BaseResponse> responseResponseStatusException(ResponseStatusException exception){
        BaseResponse response = CommonResponseWithoutData.builder()
                .statusCode(exception.getStatusCode().value())
                .message(exception.getMessage())
                .build();
        return ResponseEntity.status(exception.getStatusCode()).body(response);
    }
}
