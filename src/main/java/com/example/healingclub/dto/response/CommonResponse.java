package com.example.healingclub.dto.response;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonResponse <T> extends BaseResponse{
    private Integer statusCode;
    private String message;
    private T data;
}
