package com.ordina.jworks.graphqldemo.graphqlbff.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.ordina.jworks.graphqldemo.graphqlbff.carclient.model.Car;
import com.ordina.jworks.graphqldemo.graphqlbff.employeeclient.model.Employee;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class QueryResolver implements GraphQLQueryResolver{

    public Employee getEmployee(Integer id) {
        //TODO implement method
        return null;
    }

    public Collection<Employee> getEmployees() {
        //TODO implement method
        return null;
    }

    public Car getCar(Integer id){
        //TODO implement method
        return null;
    }

    public Collection<Car> getCars(){
        //TODO implement method
        return null;
    }
}
