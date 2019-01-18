package com.ordina.jworks.graphqldemo.graphqlbff.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.ordina.jworks.graphqldemo.graphqlbff.employeeclient.model.ContractType;
import com.ordina.jworks.graphqldemo.graphqlbff.employeeclient.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class MutationResolver implements GraphQLMutationResolver {

    public Employee addEmployee(String firstName,
                                String lastName,
                                String email,
                                String mobile,
                                String carLicensePlate,
                                ContractType contractType
                                ) {
        //TODO implement method
        return null;
    }

}
