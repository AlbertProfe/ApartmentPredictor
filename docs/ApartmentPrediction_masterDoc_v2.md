# ApartmentPredictor masterDoc v2

## Summary

### All References

- Reference project: [Spring Boot: H2 DB and Thymeleaf – albertprofe wiki](https://albertprofe.dev/springboot/boot-what-create-th-h2.html)
- Microservices: https://spring.io/]
- Spring Boot is open-source: [GitHub - spring-projects/spring-boot: Spring Boot helps you to create Spring-powered, production-grade applications and services with absolute minimum fuss.](https://github.com/spring-projects/spring-boot)
- Spring Boot Guides / Academy: https://spring.io/guides / https://spring.academy/courses
- Quickstart: https://spring.io/quickstart

Historical notes:

- [Spring Framework – albertprofe wiki](https://albertprofe.dev/spring/spring-basics.html)
- [JPA Before Annotatons one-to-many xml](https://github.com/AlbertProfe/ApartmentPredictor/blob/master/docs/appends/JPA_Before_Annotatons:_one-to-many-xml.md)

#### Api Rest references

- What is api rest:
  
  - [Network: API Rest – albertprofe wiki](https://albertprofe.dev/devops/devops-network-rest.html)
  - [What is a REST API?](https://www.redhat.com/en/topics/api/what-is-a-rest-api) /  [What is a REST Api, GoogleCloud](https://cloud.google.com/discover/what-is-rest-api)

- API REST test tools:
  
  - [postman](https://www.postman.com/) / [Postman docs](https://learning.postman.com/docs/sending-requests/requests/)
  - [Swagger](https://swagger.io/) / [OpenApi](https://springdoc.org/)

- [restaurantManager controller](https://github.com/AlbertProfe/restaurantManager/blob/master/src/main/java/dev/example/restaurantManager/controller/BookingController.java)

- [Booking Controller](https://github.com/AlbertProfe/restaurantManager/blob/master/src/main/java/dev/example/restaurantManager/controller/BookingController.java) / [LibraryRestController](https://github.com/AlbertProfe/viladoms2022books/blob/master/libraryRest/src/main/java/com/example/myfirstprojectspring/LibraryRestController.java)

### Product Goal

> Use this small housing-price dataset to train a regularized regression model that handles multicollinearity, then expose predictions through a Spring Boot REST API and a React form UI where users input area, bedrooms, bathrooms, furnishing, and road proximity for instant price estimates.

We are defining a clear full-stack goal: implement RESTful CRUD endpoints in Spring Boot for Apartment and Post entities, then build React views to list, create, edit, delete, and assign posts to apartments with a simple, responsive UI.

From source:

> A simple yet challenging project, to predict the housing price based on certain factors like house area, bedrooms, furnished, nearness to mainroad, etc.
> 
> The dataset is small yet, it's complexity arises due to the fact that it has strong <mark>multicollinearity</mark>.

### Version goal

> We are defining a Rest Controller in Spring Boot for Apartment and Review entities, then test them to list, create, edit, delete, and assign reviews to apartments with <mark>Postman</mark> test tool.

## Project commits

- [Commits · AlbertProfe/ApartmentPredictor · GitHub](https://github.com/AlbertProfe/ApartmentPredictor/commits/master/)

### Project structure

```textile
.
├── main
│   ├── java
│   │   └── com
│   │       └── example
│   │           └── apartment_predictor
│   │               ├── ApartmentPredictorApplication.java
│   │               ├── model
│   │               │   ├── Apartment.java
│   │               │   ├── Owner.java
│   │               │   ├── Reviewer.java
│   │               │   └── Review.java
│   │               ├── controller
│   │               │   ├── ApartmentRestController.java
│   │               ├── repository
│   │               │   ├── ApartmentRepository.java
│   │               │   └── ReviewRepository.java
│   │               └── service
│   │                   ├── ApartmentService.java
│   │                   └── ReviewService.java
│   └── resources
│       ├── application.properties
│       ├── static
│       └── templates
└── test
    └── java
        └── com
            └── example
                └── apartment_predictor
                    └── ApartmentPredictorApplicationTests.java

17 directories, 11 files
```

## Data Model

Data source `csv`

- [Kaggle HousePrices datasheet](https://www.kaggle.com/datasets/yasserh/housing-prices-dataset/data)
- Raw cleanned data:
  - https://raw.githubusercontent.com/ywchiu/riii/master/data/house-prices.csv
  - https://raw.githubusercontent.com/YBI-Foundation/Dataset/main/House%20Prices.csv

### Java classes

```java
// java class
/**
 * The Apartment class models the details of a residential property. 
 * It stores pricing and structural information such as price, area, 
 * number of bedrooms, bathrooms, and floors (stories). It also tracks 
 * property features like main road access, guest room, basement, 
 * hot water, heating, air conditioning, and parking availability. 
 * Additionally, it includes preferences related to furnishing 
 * (furnished, semi-furnished, or unfurnished) and property status. 
 * This class provides getter and setter methods for encapsulated 
 * data access and supports comparison, filtering, and reporting 
 * operations in real estate applications.
 */

public class Apartment {

    private String id;
    private Long price;
    private Integer area;
    private Integer bedrooms;
    private Integer bathrooms;
    private Integer stories;
    private String mainroad;
    private String guestroom;
    private String basement;
    // refactor both fiels to one
    private String hotwaterheating;
    //private String hotwater;
    //private String heating;
    private String airconditioning;
    private Integer parking;
    private String prefarea;
    private String furnishingstatus;
    // @OneToMany
    private List reviews;
}
```

```java
public class Owner {

    private  String id;
    private String name;
    private String email;
    private int age;
    private boolean isActive;
    private boolean isBusiness;
    private String idLegalOwner;
    private LocalDate registrationDate;
    private int qtyDaysAsOwner;
}
```

```java
public class Review {

    private String id;
    private String title;
    private String content;
    private int rating;
    private LocalDate reviewDate;
    // @ManyToOne
    private Apartment aparment;
```

## UML V2.0 ApartmentPredictor

- [UML mermaid](https://github.com/AlbertProfe/ApartmentPredictor/blob/master/docs/appends/ApartmentPredictor-uml_v1.md)

![](https://raw.githubusercontent.com/AlbertProfe/ApartmentPredictor/refs/heads/master/docs/diagrams/ApartmentPredictor-uml_v1.png)

## Code

### MC

> This architecture defines a simple Spring Boot testing setup where a Test component exercises a `Service` layer that delegates to a `Repository`, which in turn <mark>performs CRUD operations against an in-memory/local H2 database. </mark>
> 
> **It is MC (Model-Controller) layered**, with Test invoking Service (buisness logic) → Repository (data access) → DB, enforcing s<mark>eparation of concerns. </mark>
> 
> The goal is to:
> 
> - validate application logic and data access without external infrastructure, enabling fast, isolated tests.
> 
> - validate **Rest Controle**r layer and <mark>data publising with Postman tool.</mark>

![](https://raw.githubusercontent.com/AlbertProfe/ApartmentPredictor/refs/heads/master/docs/diagrams/mc_uml-junittest_v1.png)

### Service

> The [ApartmentService.java](cci:7://file:///home/albert/MyProjects/Sandbox/ApartmentPredictorProject/ApartmentPredictor/src/main/java/com/example/apartment_predictor/service/ApartmentService.java:0:0-0:0) is a Spring service class annotated with `@Service` that provides <mark>business logic for apartment operations</mark>. 
> 
> It contains method stubs for CRUD operations including [findAll()](cci:1://file:///home/albert/MyProjects/Sandbox/ApartmentPredictorProject/ApartmentPredictor/src/main/java/com/example/apartment_predictor/service/ApartmentService.java:10:4-15:5), [createApartment()](cci:1://file:///home/albert/MyProjects/Sandbox/ApartmentPredictorProject/ApartmentPredictor/src/main/java/com/example/apartment_predictor/service/ApartmentService.java:18:4-18:35), [updateApartment()](cci:1://file:///home/albert/MyProjects/Sandbox/ApartmentPredictorProject/ApartmentPredictor/src/main/java/com/example/apartment_predictor/service/ApartmentService.java:20:4-20:36), [deleteApartment()](cci:1://file:///home/albert/MyProjects/Sandbox/ApartmentPredictorProject/ApartmentPredictor/src/main/java/com/example/apartment_predictor/service/ApartmentService.java:22:4-22:36), and [findApartmentById()](cci:1://file:///home/albert/MyProjects/Sandbox/ApartmentPredictorProject/ApartmentPredictor/src/main/java/com/example/apartment_predictor/service/ApartmentService.java:24:4-24:38). 

### Respository

The [ApartmentRepository.java](cci:7://file:///home/albert/MyProjects/Sandbox/ApartmentPredictorProject/ApartmentPredictor/src/main/java/com/example/apartment_predictor/repository/ApartmentRepository.java:0:0-0:0) is a `Spring Data `repository interface that extends `CrudRepository<Apartment, String>`. 

This provides basic `CRUD` (Create, Read, Update, Delete) operations for the `Apartment` entity with a String-type primary key. The interface is empty, relying on Spring Data's automatic implementation to handle standard database operations like `save()`, `findById()`, `findAll()`, and `deleteById()`. 

> The repository serves as the data access layer for apartment-related operations in the apartment predictor application.

### Rest Controller

![](https://www.gstatic.com/bricks/image/321862a0-3a14-4abb-a148-36bb8781c0f7.png)

A REST API is an [application programming interface (API)](https://www.redhat.com/en/topics/api/what-are-application-programming-interfaces) that follows the design principles of the REST architectural style. REST is short for **representational state transfer**, and is a set of rules and guidelines about how you should build a web API.

#### What is a REST controller?

In **Spring Boot**, a **REST controller** is a Java class that:

- **Receives HTTP requests** from clients (browser, Postman, frontend app)
- **Runs your backend logic** (usually by calling a `Service`)
- **Returns data** (usually JSON) as the HTTP response
- Methods annotated with **request mappings**:
  - (e.g., `@GetMapping`, `@PostMapping`) handle web requests, HTTP.

In code, it’s typically a class annotated with:

- `@RestController`

`@RestController` is basically:

- `@Controller` (it’s a web controller)
- plus `@ResponseBody` by default (so return values are written directly to the HTTP response body, commonly as JSON)

So when your method returns an [Apartment](cci:2://file:///home/albert/MyProjects/Sandbox/ApartmentPredictorProject/ApartmentPredictor/src/main/java/com/example/apartment_predictor/model/Apartment.java:8:0-219:1), Spring automatically serializes it into JSON.

#### What is an endpoint created by a REST controller?

An **endpoint** is a specific **HTTP route** (URL + HTTP method) that your backend exposes.

An endpoint is defined by combining:

- **Base path** on the controller (example: `@RequestMapping("api/apartment")`)
- **Method path** on a handler (example: `@GetMapping("/getById/{id}")`)
- **HTTP method** (`GET`, `POST`, `PUT`, `DELETE`, etc.)

#### Apartment Controller

```java
@RestController
@RequestMapping("api/apartment")
public class ApartmentRestController {

    @Autowired
    ApartmentService apartmentService;

    @GetMapping("/getAll")
    public Iterable<Apartment> getAllApartments(){
        return apartmentService.findAll();
    }


    @GetMapping("/getById")
    public Apartment getApartmentById(@RequestParam String id){
        return apartmentService.findApartmentById(id);

    }
}
```

#### PathVariable vs RequestParam

![](https://raw.githubusercontent.com/AlbertProfe/ApartmentPredictor/refs/heads/master/docs/screenshots/postman-getById-1.png)

Using `@PathVariable`

**Controller mapping**

```java
@GetMapping("/getById/{id}")
public Apartment getApartmentById(@PathVariable String id) {
    return apartmentService.findApartmentById(id);
}
```

**How you call it**

```text
GET http://localhost:8080/api/apartment/getById/19a1b4c3-cfc8-4db2-885c-546db0511463
```

**Key point**

- The `id` is part of the URL path.
- Best fit for “resource by id” in REST style (`/apartments/{id}` pattern).

![](https://raw.githubusercontent.com/AlbertProfe/ApartmentPredictor/refs/heads/master/docs/screenshots/postman-getById-2.png)

Using `@RequestParam` (query parameter style)

**Controller mapping**

```java
@GetMapping("/getById")
public Apartment getApartmentById(@RequestParam String id) {
    return apartmentService.findApartmentById(id);
}
```

**How you call it**

```text
GET http://localhost:8080/api/apartment/getById?id=19a1b4c3-cfc8-4db2-885c-546db0511463
```

**Key point**

- The `id` is passed as a query string parameter (`?id=...`).
- Often used for filtering/search endpoints, but can also be used for “by id” if you prefer.

##### Optional: support *both* styles at once

You can overload two methods (different mappings) so **both URLs work**:

- `/getById/{id}`
- `/getById?id=...`

```java
@GetMapping("/getById/{id}")
public Apartment getApartmentByIdPath(@PathVariable String id) {
    return apartmentService.findApartmentById(id);
}

@GetMapping("/getById")
public Apartment getApartmentByIdParam(@RequestParam String id) {
    return apartmentService.findApartmentById(id);
}
```

Calls that will work

- **PathVariable style**
  
  ```text
  GET http://localhost:8080/api/apartment/getById/19a1b4c3-cfc8-4db2-885c-546db0511463
  ```

- **RequestParam style**
  
  ```text
  GET http://localhost:8080/api/apartment/getById?id=19a1b4c3-cfc8-4db2-885c-546db0511463
  ```

#### POST, PUT & PATCH to update

- [REST-CRUD_updatePUT](https://github.com/AlbertProfe/ApartmentPredictor/blob/master/docs/appends/REST-CRUD_updatePUT.md)

#### Request/response Cycle

- [Spring Boot: cycle – albertprofe wiki](https://albertprofe.dev/springboot/boot-what-cycle.html)

Get operation: GET Cycle: Get all Apartments.



![](https://raw.githubusercontent.com/AlbertProfe/ApartmentPredictor/refs/heads/master/docs/screenshots/code-restcontroller.png)

Post operation: POST Cycle: Create New Apartment

This cycle involves sending new data to be persisted in the Database.

**Step-by-Step Breakdown:**

1. **Request:** The user sends a JSON body via Postman to the `/create` endpoint.
2. **Controller:** Captures the JSON and converts it into an `Apartment` object.
3. **Service:** Receives the object and triggers the `save()` method.
4. **Repository:** Executes an `INSERT` SQL command.

Link: 

- [Request/response Cycle](https://github.com/alexpjava/ApartmentPredictorAlpy/blob/main/docs/flow_request_response_cycle_en.md) by Alex P.

![](https://raw.githubusercontent.com/AlbertProfe/ApartmentPredictor/refs/heads/master/docs/diagrams/UML-reqres-RESTController.png)

#### Postman documentation API REST

- [apartmentPredictorCRUD](https://documenter.getpostman.com/view/7473960/2sBXVeFs8L)

#### ResponseBody

- [Lab#SB08-3: H2 and API Rest – albertprofe wiki](https://albertprofe.dev/springboot/sblab8-3.html#responseentity)

`ResponseEntity` is Spring’s **full HTTP response wrapper (container)**. Returning `Iterable<Apartment>` only sends the JSON body, but returning `ResponseEntity<Iterable<Apartment>>` lets you control **everything** about the HTTP response: status code, headers, and body.

Why it’s relevant

- **Status control**: You can return `200 OK`, `201 CREATED`, `204 NO_CONTENT`, `404 NOT_FOUND`, etc., depending on business logic. This makes your API *semantic* (clients can react properly).
- **Headers**: You can attach metadata like versioning, caching (`Cache-Control`), pagination info, custom flags, or diagnostics (like your `"Status"` header). Clients and gateways can use headers without parsing the body.
- **Cleaner error handling**: You can return a different body for errors (e.g., an error DTO) with the correct status, instead of always `200`.

How it works

`ResponseEntity` is a container with:

- **HTTP status** (default is 200 if you use `ok()`)
- **HTTP headers**
- **Body** (your `Iterable<Apartment>`)

Spring MVC uses this to build the actual HTTP response. It then uses Jackson to serialize the body to JSON, and writes headers/status to the network response.  

#### Nested Objects

> From previous version we had the 1:n Apartment <> Review OneToMany configured with JPA.

```java
@Entity
public class Apartment {

    @Id
    protected String id;
    private Long price;
    protected Integer area;
    protected Integer bedrooms;
    private Integer bathrooms;
    private Integer stories;
    private String mainroad;
    private String guestroom;
    private String basement;
    private String hotwaterheating;
    private String airconditioning;
    private Integer parking;
    private String prefarea;
    private String furnishingstatus;

    @OneToMany(
            mappedBy = "apartment",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Review> reviews = new ArrayList<>();
}

@Entity
public class Review {

    @Id
    private String id;
    private String title;
    private String content;
    private int rating;
    private LocalDate reviewDate;
    @JsonIgnore
    @JoinColumn(name = "apartment_fk")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Apartment apartment;
}
```

`@JsonIgnore` is there to prevent **infinite recursion (circular reference)** and/or overly large JSON when you serialize your JPA entities to JSON (typically in REST responses).

**What causes the problem in `Apartment` ↔ `Review`**

In a bidirectional relationship you usually have:

- `Apartment` has `List<Review> reviews` (`@OneToMany(mappedBy="apartment")`)
- `Review` has `Apartment apartment` (`@ManyToOne`)

When Jackson (the JSON serializer Spring Boot uses) tries to serialize an `Apartment`, it will include:

1. the apartment fields
2. the `reviews` list
3. for each `Review`, it serializes its fields, including `review.apartment`
4. that `apartment` again contains `reviews`
5. repeat forever → **StackOverflowError** / “Infinite recursion” exception

Putting `@JsonIgnore` on `Review.apartment` breaks that loop by saying:

- **Serialize `Review`, but do not include its `apartment` property in JSON.**

So you can safely return an `Apartment` with its reviews, and each review won’t contain the whole apartment again.

Why it’s on the `ManyToOne` side in your case

Common API shape:

- `GET /apartments/{id}` returns the apartment and its `reviews`
- Each review doesn’t need to embed the full apartment again

So ignoring `Review.apartment` is usually the simplest choice.

> Important: it’s not a JPA requirement : `@JsonIgnore` is **only for JSON serialization**, not for the database relationship. JPA doesn’t need it.

##### Alternatives (often better than `@JsonIgnore`)

If you want *some* apartment info inside a review (or you want both directions sometimes), consider:

- **`@JsonManagedReference` / `@JsonBackReference`** (classic parent/child handling)
- **`@JsonIdentityInfo`** (serialize objects by id to avoid loops)
- **DTOs** (best practice for non-trivial APIs: return `ReviewDto`, `ApartmentDto` instead of entities)

##### Cascade

Unrelated to `@JsonIgnore`, but worth flagging: `@JoinColumn` should typically go with `@ManyToOne`, but **`@ManyToOne` generally should not use `cascade = CascadeType.ALL`** (deleting a review could delete its apartment). Usually you only cascade from parent (`Apartment`) to children (`Review`), not the other way around.

## JPA

### Summary

- [Getting Started :: Spring Data JPA](https://docs.spring.io/spring-data/jpa/reference/jpa/getting-started.html)

- [Core concepts :: Spring Data JPA](https://docs.spring.io/spring-data/jpa/reference/repositories/core-concepts.html)

- [Spring Boot: Data &amp; DB – albertprofe wiki](https://albertprofe.dev/springboot/boot-concepts-data.html)

Spring Data’s mission is to provide a familiar and consistent, **Spring-based programming model for data access**.

> It makes it easy to use **data access** technologies, `relational` and `non-relational` databases, `map-reduce frameworks`, and `cloud-based` data services.

This is an **umbrella project which contains many subprojects** that are specific to a given database. The projects are developed by working together with many of the companies and developers that are behind these exciting technologies.

### Relationships

- [JPA Relationships Guide](https://github.com/AlbertProfe/ApartmentPredictor/blob/master/docs/appends/JPA_Relationships_Guide.md)
- [JPA Relationships Implementation Rule](https://github.com/AlbertProfe/ApartmentPredictor/blob/master/docs/appends/JPA_RelationshipsImplementation_Rule.md)

## H2 & application.properties

> Welcome to **H2**, the Java SQL database. The main features of H2 are:
> 
> - Very fast, open source, `JDBC API`
> - Embedded and server modes; in-memory databases
> - Browser based Console application
> - Small footprint: around <mark>2.5 MB jar file size</mark>

References:

- Official web: https://h2database.com/html/installation.html
- Create H2 db from CLI: [Lab#SB08-3: H2 and API Rest – albertprofe wiki](https://albertprofe.dev/springboot/sblab8-3.html#h2-db)
- Step-by-step: [Spring Boot: H2 DB and Thymeleaf – albertprofe wiki](https://albertprofe.dev/springboot/boot-what-create-th-h2.html)
- DDL: [DDL auto generation](https://albertprofe.dev/springboot/sblab8-3.html#configuring-h2-database-in-spring-boot-with-ddl-auto-generation)
- Woking with different profiles: [Profiles and enviroment](https://albertprofe.dev/springboot/sblab8-3.html#working-on-different-environments)  
- [Lab#SB08-3: H2 and API Rest – albertprofe wiki](https://albertprofe.dev/springboot/sblab8-3.html)

Config `applcations properties` 

```properties
spring.application.name=ApartmentPredictor
# config db params connection
spring.datasource.url=jdbc:h2:/home/albert/MyProjects/Sandbox/ApartmentPredictorProject/db/apartmentpredictordb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=albert
spring.datasource.password=1234
# config db param
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=none
#spring.jpa.hibernate.ddl-auto=create
spring.jpa.hibernate.ddl-auto=update
```

This [application.properties](cci:7://file:///home/albert/MyProjects/Sandbox/rentingCarTest/rentingCar-boot/src/main/resources/application.properties:0:0-0:0) file configures a Spring Boot application for aparment price predictor management with H2 database integration.

Application Identity

- **`spring.application.name=ApartmentPredictor`** - Sets the application name used for identification in logs, monitoring tools, and service discovery. This appears in Spring Boot banners and helps distinguish this app from others.

Database Configuration

- **H2 Database Setup** - Uses H2 as an embedded/file-based database

- **Active URL**: `jdbc:h2:/home/albert/MyProjects/Sandbox/ApartmentPredictorProject/db/apartmentpredictordb` - Points to a persistent file-based H2 database stored locally

- **Commented alternatives**:
  
  - TCP server mode: `jdbc:h2:tcp://localhost/...` (for remote access)
  - In-memory mode: `jdbc:h2:mem:testdb` (data lost on restart)
    Authentication

- **Username**: `albert` (custom user, `sa` is H2's default admin)

- **Password**: `1234` (simple password for development)
  JPA/Hibernate Settings

Database Dialect and DDL

- **`spring.jpa.database-platform=org.hibernate.dialect.H2Dialect`** - Tells Hibernate to use H2-specific SQL syntax
- **`spring.jpa.show-sql=true`** - Enables SQL query logging for debugging
- **`spring.jpa.hibernate.ddl-auto=update`** - Automatically updates database schema without dropping existing data (safer than `create` which recreates tables)

### DDL - Data Definition Language

> **DDL - Data Definition Language**
> 
> <mark>DDL (Data Definition Language)</mark> consists of SQL commands that can be used for defining, altering and deleting database structures such as tables, indexes and schemas. It simply deals with descriptions of the database schema and is used to create and modify the structure of database objects in the database

![](https://raw.githubusercontent.com/AlbertProfe/ApartmentPredictor/refs/heads/master/docs/diagrams/sql-env.jpg)

### Populate DB

- [ImportCSVtoH2](https://github.com/AlbertProfe/ApartmentPredictor/blob/master/docs/appends/ImportCSVtoH2.md)

## Maven

> **Apache Maven** is a <mark>build tool for Java projects</mark>. Using a <mark>project object model </mark>(`POM`), Maven manages a project's compilation, testing, and documentation.

- [Maven](https://maven.apache.org/)

- [Maven Repository](https://mvnrepository.com/)

### pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.2.0</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.example</groupId>
    <artifactId>apartment-predictor</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>ApartmentPredictor</name>
    <description>Demo project for Spring Boot</description>
    <url/>
    <licenses>
        <license/>
    </licenses>
    <developers>
        <developer/>
    </developers>
    <scm>
        <connection/>
        <developerConnection/>
        <tag/>
        <url/>
    </scm>
    <properties>
        <java.version>21</java.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.deeplearning4j</groupId>
            <artifactId>deeplearning4j-core</artifactId>
            <version>1.0.0-M2.1</version>
        </dependency>
        <dependency>
            <groupId>org.nd4j</groupId>
            <artifactId>nd4j-native-platform</artifactId>
            <version>1.0.0-M2.1</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
```

## Tech Stack

- IDE: IntelliJ IDEA 2025.1.3 (Community Edition)
  
  - [Descargar IntelliJ IDEA](https://www.jetbrains.com/es-es/idea/download/?section=linux)
  
  - With [Installing snap on Ubuntu | Snapcraft documentation](https://snapcraft.io/docs/installing-snap-on-ubuntu): `sudo snap install intellij-idea-community --classic`

- Java 21 (or 25, 17, 11, 8) <mark>open-jdk</mark>

- <mark>JUniit 3.8.1</mark>

- Create project by <mark>Sprint Init</mark>
  
  - Alternative: Maven Project: **`maven-archetype-quickstart` archetype**
    
    - https://maven.apache.org/
    
    - mvn --version
      Apache Maven 3.8.7
      Maven home: /usr/share/maven
      Java version: 21.0.8, vendor: Ubuntu, runtime: /usr/lib/jvm/java-21-openjdk-amd64
      Default locale: en_US, platform encoding: UTF-8
      OS name: "linux", version: "6.8.0-83-generic", arch: "amd64", family: "unix"

- <mark>Spring Data JPA 4.0.0</mark>
