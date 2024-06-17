package com.example.healingclub.service;

import com.example.healingclub.dto.request.PictureRequest;
import com.example.healingclub.entity.Picture;

import java.util.List;

public interface PictureService {
    List<Picture> create(PictureRequest pictureRequest);
    void deletePictureFromImageKit(String id);
}
