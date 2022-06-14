package com.estudo.spring.resource.request;

import com.estudo.spring.domain.Car;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class CarRequest implements Serializable {
    @NotBlank
    private String brand;
    @NotBlank
    private String licensePlate;
    @NotNull
    @Positive
    private Double valueCar;

    public Car buildDomain() {
        return Car.builder()
                .valueCar(this.valueCar)
                .brand(this.brand)
                .licensePlate(this.licensePlate)
                .build();
    }

}
