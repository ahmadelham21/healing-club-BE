package com.example.healingclub.service.impl;

import com.example.healingclub.dto.request.PictureRequest;
import com.example.healingclub.entity.Picture;
import com.example.healingclub.repository.PictureRepository;
import com.example.healingclub.service.PictureService;
import io.imagekit.sdk.ImageKit;
import io.imagekit.sdk.models.FileCreateRequest;
import io.imagekit.sdk.models.results.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PictureServiceImpl implements PictureService {
    private final PictureRepository pictureRepository;
    private final ImageKit imageKit;



    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<Picture> create(PictureRequest pictureRequest) {

           return  pictureRequest.getImage().stream().map(
                    image -> {
                        try {
                            String filename = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
                            FileCreateRequest fileCreateRequest = new FileCreateRequest(image.getBytes(), filename);
                            Result result = imageKit.upload(fileCreateRequest);
                            Picture picture = new Picture();
                            picture.setUrl(result.getUrl());
                            picture.setHotel(pictureRequest.getHotel());
                            return pictureRepository.save(picture);
                        } catch (Exception e) {
                            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to upload image", e);

                        }
                    }
            ).toList();


    }

}
