package com.ordina.jworks.graphqldemo.employee;

import com.ordina.jworks.graphqldemo.employee.model.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ResourceProcessor<Resource<Employee>> employeeProcessor() {
        return new ResourceProcessor<Resource<Employee>>() {
            @Override
            public Resource<Employee> process(Resource<Employee> employeeResource) {
                employeeResource.add(new Link(String.format("http://localhost:8081/cars/search/carByLicensePlate?licensePlate=%s", employeeResource.getContent().getCarLicencePlate()), "car"));
                return employeeResource;
            }
        };
    }
}
