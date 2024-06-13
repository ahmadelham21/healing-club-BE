package com.example.healingclub.dto.request;


import com.example.healingclub.entity.Hotel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PictureRequest {
    private Hotel hotel;
    private List<MultipartFile> image;
}
