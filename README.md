# GraphQL workshop
This project contains 2 microservices: **employee** and **fleet**, which are parts of an imaginary Ordina ERP system. 

**Employee** microservice exposes REST endpoints for CRUD operations on the ```Employee``` resource. This is a Spring boot application. 
It has its own embedded H2 database. ```Employee``` resource contains ```carLicencePlate``` field that can be used as a unique identifier of 
a company car driven by the employee

**Fleet** microservice manages Ordina fleet and it exposes the ```Car``` resource. As the Employee microservice, Fleet 
microservice is also a Spring boot application and it also has its own embedded H2 database. 

*Disclamer*. For the sake of simplicity, both microservices were written using Spring Data Rest library and no service 
discovery is used. The links are hardcoded. Please, do not use this approach in production.

## Aim of this hands-on

The aim of this hands-on session is to wrap the REST API into GraphQL by building a the **graphql-bff** microservice.
Most of the code, which is irrelevant to GraphQL is already written for you. Your task is to write the GraphQL 
functionality. 

For this, we are going to use [GraphQL Java Kickstart libraries](https://www.graphql-java-kickstart.com/).

## Task 1

Add necessary dependencies to ```pom.xml``` of the **graphql-bff** microservice:

- GraphQL Spring boot starter

```xml
<dependency>
    <groupId>com.graphql-java-kickstart</groupId>
    <artifactId>graphql-spring-boot-starter</artifactId>
    <version>5.2</version>
</dependency>
```
This starter will take care of the GraphQL configuration. It will also starts the GraphQl servlet with the default
endpoint ```POST /graphql```. All queries should be sent to this endpoint

- GraphiQL Spring boot starter

```xml
<dependency>
   <groupId>com.graphql-java-kickstart</groupId>
   <artifactId>graphiql-spring-boot-starter</artifactId>
   <version>5.2</version>
</dependency>
```
This starter will autoconfigure GraphiQL tool (*a la* Postman for GraphQL) for the interactive testing. It is accessible
at the path ```/graphiql```

- Voyager Spring boot starter

```xml
<dependency>
   <groupId>com.graphql-java-kickstart</groupId>
   <artifactId>voyager-spring-boot-starter</artifactId>
   <version>5.2</version>
</dependency>
```
This starter will autoconfigure Voyager tool for the schema visualization. It is accessible at ```/voyager``` and 
works with Firefox browser

Run ```mvn clean install``` from the root ```pom.xml```. The project should compile but the integration tests will
fail. Now it is time to start implementing GraphQL.

## Task 2

Create schema in the file ```src/resources/schema.graphqls```. This is a default file location. The schema will 
automatically be picked up and parsed by the GraphQL engine.
The schema should contain root queries for getting:

- a list of employees
- a single employee by id
- a list of cars
- a single car by id

and 

- a mutation to create a new employee.

When defining fields of the types, use ```Employee``` and ```Car``` entities from the packages ```employeeclient```
and ```carclient```, respectively.

Embed ```Car``` type as a field in the ```Employee``` type. **Do not add ```Car``` field in the ```Employee``` 
entity. Do this in the schema only**.

When creating the schema you can use examples from the presentation or from the GraphQL website [https://graphql.github.io/learn/schema/](https://graphql.github.io/learn/schema/)


## Task 3

Implement resolver methods to resolve the root queries in your schema. The method stubs you can find in 
the class ```QueryResolver```. For this you can used the provided Feign clients with the respective
methods. Use normal Spring autowiring, for the client instantiation.

**Names of the resolver functions should be the same as the name of the field to be resolved or in 
may have a prefix "get"**.

## Task 4

Implement the Car resolver method in the class ```EmployeeResolver```. Use car license plate to search for the Car object. Run the respective integration tests  


## Task 5

Implement the mutation resolver method to add a new employee in the class ```MutationResolver```.

## Task 6
Start up all applications one by one from your IDE. The **graphql-bff** should in this case be run with  spring profile ```local```.

Alternatively, start up the whole setup by running:

```docker-compose up --build -d```

Test the API with the GraphiQL tool that should be accessible at ```http://localhost:8082/graphiql``` 

Visualize the schema with the Voyager tool by accessing ```http://localhost:8082/voyager``` with Firefox


## Task 7 (homework, advanced features)

- implement DataLoader instead of the async method to resolve ```Car``` in the ```Employer``` type
- convert REST endpoints in the **employee** and **fleet** microservices into GraphQL endpoints
- implement GraphQL clients in the **graphql-bff** microservice instead of Feign clients

