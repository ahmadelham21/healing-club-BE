package com.example.healingclub.dto.request;


import com.example.healingclub.model.entity.Hotel;
import com.example.healingclub.model.entity.Picture;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PictureRequest {
    private Hotel hotel;
    private MultipartFile image;



}
