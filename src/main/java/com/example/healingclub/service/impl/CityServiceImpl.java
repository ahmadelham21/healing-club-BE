package com.example.healingclub.service.impl;

import com.example.healingclub.entity.City;
import com.example.healingclub.repository.CityRepository;
import com.example.healingclub.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    @Override

    @Transactional(rollbackFor = Exception.class)
    public City create(City city) {
        return cityRepository.saveAndFlush(city);
    }

    @Transactional(readOnly = true)
    @Override
    public City getById(String id) {
        return cityRepository.findById(id).orElseThrow(()-> new  ResponseStatusException(HttpStatus.NOT_FOUND,"Data not found"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<City> getAll() {
        return cityRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public City update(City city) {
        cityRepository.findById(city.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Data not found"));
        return cityRepository.saveAndFlush(city);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(String id) {
        City city = cityRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found"));
        cityRepository.delete(city);
    }
}
