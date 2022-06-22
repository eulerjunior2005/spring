package com.estudo.spring.resource.request;

import com.estudo.spring.domain.Car;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class CarRequest implements Serializable {
    @NotBlank(message = "000002")
    private String brand;
    @NotBlank(message = "000002")
    private String licensePlate;
    @NotNull(message = "000003")
    @Positive(message = "000004")
    private Double valueCar;

    public Car buildDomain() {
        return Car.builder()
                .valueCar(this.valueCar)
                .brand(this.brand)
                .licensePlate(this.licensePlate)
                .build();
    }

}
