package com.example.healingclub.service;

import com.example.healingclub.dto.request.PictureRequest;
import com.example.healingclub.model.entity.Picture;
import org.springframework.web.multipart.MultipartFile;

public interface PictureService {
    Picture create(PictureRequest pictureRequest);
}
