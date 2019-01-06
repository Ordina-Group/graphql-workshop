package com.ordina.jworks.graphqldemo.graphqlbff.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.ordina.jworks.graphqldemo.graphqlbff.carclient.model.Car;
import com.ordina.jworks.graphqldemo.graphqlbff.carclient.CarClient;
import com.ordina.jworks.graphqldemo.graphqlbff.employeeclient.model.Employee;
import com.ordina.jworks.graphqldemo.graphqlbff.employeeclient.EmployeeClient;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class QueryResolver implements GraphQLQueryResolver{

    private final EmployeeClient employeeClient;
    private final CarClient carClient;


    public QueryResolver(EmployeeClient employeeClient, CarClient carClient) {
        this.employeeClient = employeeClient;
        this.carClient = carClient;
    }

    public Employee getEmployee(Integer id, DataFetchingEnvironment env) {
        return employeeClient.getEmployee(id).getContent();
    }

    public Collection<Employee> getEmployees(DataFetchingEnvironment env) {
        Collection<Employee> content = employeeClient.getEmployees().getContent();
        return content;
    }

    public Car getCar(Integer id){
        Resource<Car> car = carClient.getCar(id);
        return car.getContent();
    }

    public Collection<Car> getCars(){
        return carClient.getCars().getContent();
    }
}
