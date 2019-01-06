package com.ordina.jworks.graphqldemo.graphqlbff;

import com.ordina.jworks.graphqldemo.graphqlbff.carclient.CarClient;
import com.ordina.jworks.graphqldemo.graphqlbff.carclient.model.Car;
import com.ordina.jworks.graphqldemo.graphqlbff.employeeclient.EmployeeClient;
import com.ordina.jworks.graphqldemo.graphqlbff.employeeclient.model.ContractType;
import com.ordina.jworks.graphqldemo.graphqlbff.employeeclient.model.Employee;
import io.restassured.http.ContentType;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.util.Lists.newArrayList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verifyZeroInteractions;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTest {

    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";
    private static final String EMAIL = "john.doe.@ordina.be";
    private static final String MOBILE = "0486654897";
    private static final Integer CAR_ID = 1;
    private static final String MAKE = "BMW";
    private static final String COLOR = "yellow";
    private static final String MODEL = "318d";
    private static final String BODY_TYPE = "touring";
    private static final String LICENCE_PLATE = "1-GHT-678";

    @MockBean
    private CarClient carClient;

    @MockBean
    private EmployeeClient employeeClient;

    @LocalServerPort
    private int port;


    @Test
    public void contextLoads() {

    }


    @Test
    public void getEmployeeShouldReturnOnlyAskedFields() {

        Mockito.when(employeeClient.getEmployee(3)).thenReturn(getEmployeeResource());
        Mockito.when(carClient.findCarByLicensePlate(LICENCE_PLATE)).thenReturn(getCarResource());

        given()
            .port(port)
            .contentType(ContentType.JSON)
            .body("{\"query\":\"{getEmployee(id:3){firstName lastName car{make}}}\"}")
            .post("/graphql")
            .then()
            .assertThat().statusCode(200)
            .body("data.getEmployee.firstName", equalTo(FIRST_NAME))
            .body("data.getEmployee.lastName", equalTo(LAST_NAME))
            .body("data.getEmployee.email", nullValue())
            .body("data.getEmployee.mobile", nullValue())
            .body("data.getEmployee.car.make", equalTo(MAKE))
            .body("data.getEmployee.car.color", nullValue())
            .body("data.getEmployee.car.bodyType", nullValue())
            .body("data.getEmployee.car.model", nullValue());
    }

    @Test
    public void getEmployeeShouldReturnOnlyAskedFieldsAndNotCallCarClient() {

        Mockito.when(employeeClient.getEmployee(3)).thenReturn(getEmployeeResource());

        given()
            .port(port)
            .contentType(ContentType.JSON)
            .body("{\"query\":\"{getEmployee(id:3){firstName lastName }}\"}")
            .post("/graphql")
            .then()
            .assertThat().statusCode(200)
            .body("data.getEmployee.firstName", equalTo(FIRST_NAME))
            .body("data.getEmployee.lastName", equalTo(LAST_NAME))
            .body("data.getEmployee.email", nullValue())
            .body("data.getEmployee.mobile", nullValue())
            .body("data.getEmployee.car.make", nullValue())
            .body("data.getEmployee.car.color", nullValue())
            .body("data.getEmployee.car.bodyType", nullValue())
            .body("data.getEmployee.car.model", nullValue());

        verifyZeroInteractions(carClient);

    }

    @Test
    public void getEmployeesShouldReturnOnlyAskedFields() {

        Mockito.when(employeeClient.getEmployees()).thenReturn(getEmployeesResources());
        Mockito.when(carClient.findCarByLicensePlate(LICENCE_PLATE)).thenReturn(getCarResource());

        given()
            .port(port)
            .contentType(ContentType.JSON)
            .body("{\"query\":\"{getEmployees{firstName lastName car{make}}}\"}")
            .post("/graphql")
            .then()
            .assertThat().statusCode(200)
            .and().log().body()
            .body("data.getEmployees.firstName[0]", equalTo(FIRST_NAME))
            .body("data.getEmployees.lastName[0]", equalTo(LAST_NAME))
            .body("data.getEmployees.email[0]", nullValue())
            .body("data.getEmployees.mobile[0]", nullValue())
            .body("data.getEmployees.car.make[0]", equalTo(MAKE))
            .body("data.getEmployees.car.color[0]", nullValue())
            .body("data.getEmployees.car.bodyType[0]", nullValue())
            .body("data.getEmployees.car.model[0]", nullValue());

    }

    @Test
    public void getEmployeesShouldReturnOnlyAskedFieldsAndNotCallCarClient() {

        Mockito.when(employeeClient.getEmployees()).thenReturn(getEmployeesResources());

        given()
            .port(port)
            .contentType(ContentType.JSON)
            .body("{\"query\":\"{getEmployees{firstName lastName}}\"}")
            .post("/graphql")
            .then()
            .assertThat().statusCode(200)
            .and().log().body()
            .body("data.getEmployees.firstName[0]", equalTo(FIRST_NAME))
            .body("data.getEmployees.lastName[0]", equalTo(LAST_NAME))
            .body("data.getEmployees.email[0]", nullValue())
            .body("data.getEmployees.mobile[0]", nullValue())
            .body("data.getEmployees.car.make[0]", nullValue())
            .body("data.getEmployees.car.color[0]", nullValue())
            .body("data.getEmployees.car.bodyType[0]", nullValue())
            .body("data.getEmployees.car.model[0]", nullValue());

        verifyZeroInteractions(carClient);
    }

    @Test
    public void getCarShouldReturnOnlyAskedFields() {

        Mockito.when(carClient.getCar(1)).thenReturn(getCarResource());

        given()
            .port(port)
            .contentType(ContentType.JSON)
            .body("{\"query\":\"{getCar(id:1) {make bodyType}}\"}")
            .post("/graphql")
            .then()
            .assertThat().statusCode(200)
            .body("data.getCar.make", equalTo(MAKE))
            .body("data.getCar.color", nullValue())
            .body("data.getCar.bodyType", equalTo(BODY_TYPE))
            .body("data.getCar.model", nullValue());
    }

    @Test
    public void getCarsShouldReturnOnlyAskedFields() {

        Mockito.when(carClient.getCars()).thenReturn(getCarResources());

        given()
            .port(port)
            .contentType(ContentType.JSON)
            .body("{\"query\":\"{getCars{make bodyType}}\"}")
            .post("/graphql")
            .then()
            .assertThat().statusCode(200)
            .body("data.getCars.make[0]", equalTo(MAKE))
            .body("data.getCars.color[0]", nullValue())
            .body("data.getCars.bodyType[0]", equalTo(BODY_TYPE))
            .body("data.getCars.model[0]", nullValue());
    }

    @Test
    public void addEmployeeShouldCallEmployeeClientWithCorrectArguments() {
        Mockito.when(employeeClient.save(refEq(getEmployee()))).thenReturn(getEmployeeResource());

        given()
            .port(port)
            .contentType(ContentType.JSON)
            .body("{\"query\":\"mutation{addEmployee(firstName: \\\"John\\\", lastName: \\\"Doe\\\", email: \\\"john.doe.@ordina.be\\\", mobile: \\\"0486654897\\\", carLicensePlate: \\\"1-GHT-678\\\", contractType: PERMANENT) {firstName lastName mobile email}}\"}")
            .post("/graphql")
            .then()
            .assertThat().statusCode(200)
            .and().log().body()
            .body("data.addEmployee.firstName", equalTo(FIRST_NAME))
            .body("data.addEmployee.lastName", equalTo(LAST_NAME))
            .body("data.addEmployee.email", equalTo(EMAIL))
            .body("data.addEmployee.mobile", equalTo(MOBILE));
    }

    private Resource<Employee> getEmployeeResource() {
        Employee employee = getEmployee();
        return new Resource<>(employee, newArrayList());
    }

    @NotNull
    private Employee getEmployee() {
        Employee employee = new Employee();
        employee.setFirstName(FIRST_NAME);
        employee.setLastName(LAST_NAME);
        employee.setEmail(EMAIL);
        employee.setMobile(MOBILE);
        employee.setCarLicencePlate(LICENCE_PLATE);
        employee.setContractType(ContractType.PERMANENT);
        return employee;
    }

    private Resources<Employee> getEmployeesResources() {
        List<Employee> employees = newArrayList(getEmployeeResource().getContent());
        return new Resources<>(employees, newArrayList());
    }

    private Resource<Car> getCarResource() {
        Car car = new Car();
        car.setId(CAR_ID);
        car.setMake(MAKE);
        car.setColor(COLOR);
        car.setModel(MODEL);
        car.setBodyType(BODY_TYPE);
        car.setLicensePlate(LICENCE_PLATE);
        return new Resource<>(car, newArrayList());
    }

    private Resources<Car> getCarResources() {
        List<Car> cars = newArrayList(getCarResource().getContent());
        return new Resources<>(cars, newArrayList());
    }


}
