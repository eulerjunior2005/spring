package com.estudo.spring.service.impl;

import com.estudo.spring.domain.Car;
import com.estudo.spring.repository.CarRepository;
import com.estudo.spring.resource.response.CarResponse;
import com.estudo.spring.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // injeta dependencia da propriedade final
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public List<CarResponse> findAll() {
        return carRepository.findAll()
                .stream()
                .map(this::buildResponse)
                .collect(Collectors.toList());
    }

    private CarResponse buildResponse(Car car) {
        return CarResponse.builder()
                .valueCar(car.getValueCar())
                .brand(car.getBrand())
                .id(car.getId())
                .licensePlate(car.getLicensePlate())
                .build();
    }

    @Override
    public CarResponse findById(Integer id) {
        return buildResponse(carRepository.findById(id).orElseThrow(RuntimeException::new));
    }

    @Override
    public CarResponse create(Car car) {
        return buildResponse(carRepository.save(car));
    }

    @Override
    public CarResponse update(Integer id, Car car) {
        CarResponse retrievedCar = this.findById(id);
        car.setId(retrievedCar.getId());
        return buildResponse(carRepository.save(car));
    }

    @Override
    public void delete(Integer id) {
        CarResponse retrievedCar = this.findById(id);
        carRepository.deleteById(retrievedCar.getId());
    }
}
