package com.example.healingclub.service;

import com.example.healingclub.entity.City;

import java.util.List;

public interface CityService {
    City create(City city);
    City getById(String id);
    List<City> getAll();
    City update(City city);
    void deleteById(String id);

    City getByName(String name);

}
