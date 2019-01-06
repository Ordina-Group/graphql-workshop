package com.ordina.jworks.graphqldemo.graphqlbff.employeeclient;

import com.ordina.jworks.graphqldemo.graphqlbff.employeeclient.model.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient(name="employee")
@RequestMapping("/employees")
public interface EmployeeClient {

    @GetMapping
    Resources<Employee> getEmployees();

    @GetMapping("/{id}")
    Resource<Employee> getEmployee(@PathVariable ("id") Integer id);

    @PostMapping
    Resource<Employee> save(Employee employee);

}
