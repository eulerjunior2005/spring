package com.estudo.spring.service;

import com.estudo.spring.domain.Car;
import com.estudo.spring.resource.response.CarResponse;

import java.util.List;

public interface CarService {

    List<CarResponse> findAll();

    CarResponse findById(Integer id);

    CarResponse create(Car car);

    CarResponse update(Integer id, Car car);

    void delete(Integer id);
}
