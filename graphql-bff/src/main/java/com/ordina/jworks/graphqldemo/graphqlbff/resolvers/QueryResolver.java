package com.ordina.jworks.graphqldemo.graphqlbff.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.ordina.jworks.graphqldemo.graphqlbff.carclient.model.Car;
import com.ordina.jworks.graphqldemo.graphqlbff.employeeclient.model.Employee;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class QueryResolver implements GraphQLQueryResolver{

    public Employee getEmployee(Integer id, DataFetchingEnvironment env) {
        return null;
    }

    public Collection<Employee> getEmployees(DataFetchingEnvironment env) {
        return null;
    }

    public Car getCar(Integer id){
        return null;
    }

    public Collection<Car> getCars(){
        return null;
    }
}
