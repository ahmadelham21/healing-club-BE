package com.example.healingclub.service.impl;

import com.example.healingclub.dto.request.PictureRequest;
import com.example.healingclub.model.entity.Picture;
import com.example.healingclub.repository.PictureRepository;
import com.example.healingclub.service.PictureService;
import io.imagekit.sdk.ImageKit;
import io.imagekit.sdk.models.FileCreateRequest;
import io.imagekit.sdk.models.results.Result;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PictureServiceImpl implements PictureService {
    private final PictureRepository pictureRepository;
    private final ImageKit imageKit;



    @Transactional(rollbackFor = Exception.class)
    @Override
    public Picture create(PictureRequest pictureRequest) {
        try {
            String filename = UUID.randomUUID().toString() + "_" + pictureRequest.getImage().getOriginalFilename();
            FileCreateRequest fileCreateRequest = new FileCreateRequest(pictureRequest.getImage().getBytes(), filename);

            Result result = imageKit.upload(fileCreateRequest);

            Picture image = new Picture();
            image.setUrl(result.getUrl());
            image.setHotel(pictureRequest.getHotel());
            log.info("ERRRORRRRRRRRRR" + image.getHotel().getId() +" "+ image.getUrl());
            return pictureRepository.save(image);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to upload image", e);
        }

    }

}
