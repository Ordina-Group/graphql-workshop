package com.ordina.jworks.graphqldemo.graphqlbff.carclient;

import com.ordina.jworks.graphqldemo.graphqlbff.carclient.model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "fleet")
@RequestMapping("/cars")
public interface CarClient {

    @GetMapping
    Resources<Car> getCars();

    @GetMapping("/{id}")
    Resource<Car> getCar(@PathVariable("id") Integer id);

    @GetMapping("/search/carByLicensePlate")
    Resource<Car> findCarByLicensePlate(@RequestParam("licensePlate") String licensePlate);

    @PostMapping
    Car save(Car car);
}
