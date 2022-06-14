package com.estudo.spring.resource.response;

import com.estudo.spring.domain.Car;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarResponse implements Serializable {
    private Integer id;
    private String brand;
    private String licensePlate;
    private Double valueCar;
}
