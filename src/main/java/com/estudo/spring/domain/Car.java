package com.estudo.spring.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data // adiciona os setter and getters
@Builder
@Entity // transforma em bean para ser salvo
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String brand;
    private String licensePlate;
    //@NotNull
    @Column(nullable = false)
    private Double valueCar;
}
