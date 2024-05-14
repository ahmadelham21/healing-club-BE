package com.example.healingclub.repository;

import com.example.healingclub.model.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture,String> {
}
