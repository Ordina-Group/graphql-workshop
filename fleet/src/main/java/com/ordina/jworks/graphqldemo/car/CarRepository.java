package com.ordina.jworks.graphqldemo.car;

import com.ordina.jworks.graphqldemo.car.model.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends CrudRepository<Car, Integer> {

    @RestResource(path = "carByLicensePlate", rel = "carByLicensePlate")
    Optional<Car> findCarByLicensePlate(String licensePlate);

    List<Car> findAllByLicensePlateIn(List<String> licensePlate);
}
