package com.estudo.spring.resource;

import com.estudo.spring.resource.request.CarRequest;
import com.estudo.spring.resource.response.CarResponse;
import com.estudo.spring.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarResource {

    //@Qualifier() se tivesse mais de uma classe que implementava a interface tinha q especificar
    private final CarService carService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CarResponse> findAll(){
        return carService.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CarResponse findById(@PathVariable Integer id){
        return  carService.findById(id);
    }

    @PostMapping(value = "/create", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(code = CREATED)
    public CarResponse create(@Valid @RequestBody final CarRequest carRequest) {
        return carService.create(carRequest.buildDomain());
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id){
        carService.delete(id);
    }

    @PutMapping(value = "/update/{id}", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CarResponse update(@PathVariable Integer id, @Valid @RequestBody final CarRequest carRequest){
        return carService.update(id, carRequest.buildDomain());
    }

}
