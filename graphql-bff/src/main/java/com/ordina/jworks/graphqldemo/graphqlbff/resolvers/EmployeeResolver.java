package com.ordina.jworks.graphqldemo.graphqlbff.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.ordina.jworks.graphqldemo.graphqlbff.carclient.model.Car;
import com.ordina.jworks.graphqldemo.graphqlbff.employeeclient.model.Employee;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;


@Component
public class EmployeeResolver implements GraphQLResolver<Employee> {

    public CompletableFuture<Car> car(Employee employee){
        return null;
    }
}
