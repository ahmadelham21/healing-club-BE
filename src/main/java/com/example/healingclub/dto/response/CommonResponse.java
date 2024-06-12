package com.example.healingclub.dto.response;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonResponse <T>{
    private Integer statusCode;
    private String message;
    private T data;
    private PagingResponse paging;
}
