package com.ordina.jworks.graphqldemo.graphqlbff.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.ordina.jworks.graphqldemo.graphqlbff.carclient.CarClient;
import com.ordina.jworks.graphqldemo.graphqlbff.carclient.model.Car;
import com.ordina.jworks.graphqldemo.graphqlbff.employeeclient.model.Employee;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;


@Component
public class EmployeeResolver implements GraphQLResolver<Employee> {

    private final CarClient carClient;

    public EmployeeResolver(CarClient carClient) {
        this.carClient = carClient;
    }

    public CompletableFuture<Car> car(Employee employee){
        return CompletableFuture.supplyAsync(
            () -> carClient.findCarByLicensePlate(employee.getCarLicencePlate()).getContent());
    }
}
