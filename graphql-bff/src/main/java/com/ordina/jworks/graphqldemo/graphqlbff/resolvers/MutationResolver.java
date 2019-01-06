package com.ordina.jworks.graphqldemo.graphqlbff.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.ordina.jworks.graphqldemo.graphqlbff.employeeclient.model.ContractType;
import com.ordina.jworks.graphqldemo.graphqlbff.employeeclient.model.Employee;
import com.ordina.jworks.graphqldemo.graphqlbff.employeeclient.EmployeeClient;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class MutationResolver implements GraphQLMutationResolver {

    private final EmployeeClient employeeClient;

    public MutationResolver(EmployeeClient employeeClient) {
        this.employeeClient = employeeClient;
    }

    public Employee addEmployee(String firstName,
                                String lastName,
                                String email,
                                String mobile,
                                String carLicensePlate,
                                ContractType contractType
                                ) {
        Employee employee = getEmployee(firstName, lastName, email, mobile, carLicensePlate, contractType);

        return employeeClient.save(employee).getContent();
    }

    @NotNull
    private Employee getEmployee(String firstName, String lastName, String email, String mobile, String carLicensePlate, ContractType contractType) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setMobile(mobile);
        employee.setEmail(email);
        employee.setCarLicencePlate(carLicensePlate);
        employee.setContractType(contractType);
        return employee;
    }
}
