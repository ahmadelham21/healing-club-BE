package com.example.healingclub.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommonResponseWithoutData extends BaseResponse {
    private Integer statusCode;
    private String message;

}
