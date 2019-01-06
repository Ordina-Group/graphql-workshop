package com.ordina.jworks.graphqldemo.employee;

import com.ordina.jworks.graphqldemo.employee.model.Employee;
import org.springframework.data.repository.CrudRepository;


public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
