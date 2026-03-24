# ApartmentPredictor masterDoc v6

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

#### Pagination

-  [Paging and Sorting](https://docs.spring.io/spring-data/jpa/reference/repositories/query-methods-details.html#repositories.paging-and-sorting)  /  [Pagination](https://github.com/AlbertProfe/userBorrowBookPagination/blob/master/docs/pagination.md)  /[ Pageable: repo](https://github.com/AlbertProfe/BooksPageable)

#### Specification

- [JPA-specification-1](https://github.com/AlbertProfe/ApartmentPredictor/blob/master/docs/appends/JPA-specification-1.md) / [JPA-specification-2](https://github.com/AlbertProfe/ApartmentPredictor/blob/master/docs/appends/JPA-specification-2.md) / [JPA-specification-Apartment Plain](https://github.com/AlbertProfe/ApartmentPredictor/blob/master/docs/appends/JPA-specification-Apartment.md)

- [JPA-2026_QueryReport](https://github.com/AlbertProfe/ApartmentPredictor/blob/master/docs/appends/JPA-2026_QueryReport.md)

- [Specifications :: Spring Data JPA](https://docs.spring.io/spring-data/jpa/reference/jpa/specifications.html)

- Project reference:
  
  - [AlbertProfe/userBorrowBookFilter · GitHub](https://github.com/AlbertProfe/userBorrowBookFilter)
  
  - [repository/BorrowSpecification.java](https://github.com/AlbertProfe/userBorrowBookFilter/blob/master/userBorrowBookFilter/src/main/java/com/example/userBorrowBook/repository/BorrowSpecification.java)
  
  - [BorrowController.java](https://github.com/AlbertProfe/userBorrowBookFilter/blob/master/userBorrowBookFilter/src/main/java/com/example/userBorrowBook/controller/BorrowController.java)

### Product Goal

> Use this small housing-price dataset to train a regularized regression model that handles multicollinearity, then expose predictions through a Spring Boot REST API and a React form UI where users input area, bedrooms, bathrooms, furnishing, and road proximity for instant price estimates.

We are defining a clear full-stack goal: implement RESTful CRUD endpoints in Spring Boot for Apartment and Post entities, then build React views to list, create, edit, delete, and assign posts to apartments with a simple, responsive UI.

From source:

> A simple yet challenging project, to predict the housing price based on certain factors like house area, bedrooms, furnished, nearness to mainroad, etc.
> 
> The dataset is small yet, it's complexity arises due to the fact that it has strong <mark>multicollinearity</mark>.

### Version goal

> he refactor added `geographic` coordinate fields (`latitude` and `longitude`) to the School entity with comprehensive validation and database mapping.

This refactor enables the `School` entity to store precise geographic locations, which is essential for:

- <mark>Location-based</mark> apartment recommendations
- <mark>Distance calculations between apartments and schools</mark>
- Geographic mapping and <mark>visualization</mark> features

## Project commits

- [Commits · AlbertProfe/ApartmentPredictor · GitHub](https://github.com/AlbertProfe/ApartmentPredictor/commits/master/)

### Project structure

```textile
[Sat Feb 21 09:13:33] albert@albert-VirtualBox:~/MyProjects/Sandbox/ApartmentPredictorProject/ApartmentPredictor/src/main/java/com/example/apartment_predictor (master)
$ tree
.
├── ApartmentPredictorApplication.java
├── controller
│   ├── ApartmentAssignRestController.java
│   ├── ApartmentRestController.java
│   ├── OwnerRestController.java
│   ├── PopulateDBController.java
│   ├── PropiertyContractRestController.java
│   ├── ReviewerRestController.java
│   ├── ReviewRestController.java
│   └── SchoolRestController.java
├── model
│   ├── Apartment.java
│   ├── Duplex.java
│   ├── House.java
│   ├── Owner.java
│   ├── Person.java
│   ├── PropertyContract.java
│   ├── Reviewer.java
│   ├── Review.java
│   └── School.java
├── repository
│   ├── ApartmentRepository.java
│   ├── OwnerRepository.java
│   ├── PropertyContractRepository.java
│   ├── ReviewerRepository.java
│   ├── ReviewRepository.java
│   └── SchoolRepository.java
├── service
│   ├── ApartmentService.java
│   └── ReviewService.java
└── utils
    ├── DatabaseVerifier.java
    ├── PopulateDB.java
    └── PrintingUtils.java

6 directories, 28 files
```

## Data Model

### Java classes

```java
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
    //@OneToMany
    private List reviews;
    //@ManyToMany
    private List schools;
    //@ManyToOne
    private List contracts
}

public class School {

    private String id;
    private String name;
    private String type;
    private String location;
    private int rating;
    private boolean isPublic;

    @Column(name = "latitude")
    @NotNull(message = "Latitude is required")
    @Min(value = -90, message = "Latitude must be between -90 and 90")
    @Max(value = 90, message = "Latitude must be between -90 and 90")
    private Double latitude;

    @Column(name = "longitude")
    @NotNull(message = "Longitude is required")
    @Min(value = -180, message = "Longitude must be between -180 and 180")
    @Max(value = 180, message = "Longitude must be between -180 and 180")
    private Double longitude;
}

private String id;
    private String propertyContractCode;
    private String urlContractPropertyDocument;
    private LocalDate contractDate;
    private long valuePropertyContract;
    private String typeProperty;
    private String address;
    private boolean isActive;
    //ManyToOne
    private Owner owner;
    //ManyToOne
    private Apartment apartment;
```

```java
public class Person {

    private String id;
    private String fullName;
    private LocalDate birthDate;
    private String email;
    private String password;
    private boolean isActive;
}

public class Owner {

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
}

public class Reviewer {
    private boolean isBusiness;
    private String xAccount;
    private String webURL;
    private int qtyReviews;

}
```

### Refactor: Adding Geographic Coordinates to School Entity

> The refactor added `geographic` <mark>coordinate</mark> fields (`latitude` and `longitude`) to the [School](cci:2://file:///home/albert/MyProjects/Sandbox/ApartmentPredictorProject/ApartmentPredictor/src/main/java/com/example/apartment_predictor/model/School.java:15:0-130:1) entity with comprehensive validation and database mapping.

This refactor enables the `School` entity to store precise geographic locations, which is essential for:

- <mark>Location-based</mark> apartment recommendations
- <mark>Distance calculations between apartments and schools</mark>
- Geographic mapping and <mark>visualization</mark> features

**1. Added Import Statements:**

- `@Column` for database column mapping
- `@NotNull`, `@Min`, `@Max` for validation constraints

**2. Added Latitude Field (lines 25-29):**

```java
@Column(name = "latitude")
@NotNull(message = "Latitude is required")
@Min(value = -90, message = "Latitude must be between -90 and 90")
@Max(value = 90, message = "Latitude must be between -90 and 90")
private Double latitude;
```

**3. Added Longitude Field (lines 31-35):**

```java
@Column(name = "longitude")
@NotNull(message = "Longitude is required")
@Min(value = -180, message = "Longitude must be between -180 and 180")
@Max(value = 180, message = "Longitude must be between -180 and 180")
private Double longitude;
```

**4. Updated Constructor / Added Getters/Setters** / Updated toString() Method

- Added `latitude` and `longitude` parameters to constructor

- Initialized the new fields in constructor body

- Standard getter/setter methods for both fields

- Added latitude and longitude to string representation

Validation Rules Applied:

- **@NotNull**: Both coordinates are required fields
- **Latitude Range**: -90 to 90 degrees (valid Earth latitude range)
- **Longitude Range**: -180 to 180 degrees (valid Earth longitude range)

### UML

![](https://raw.githubusercontent.com/AlbertProfe/ApartmentPredictor/refs/heads/master/docs/diagrams/UML_v3.png)

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

![](https://raw.githubusercontent.com/AlbertProfe/ApartmentPredictor/refs/heads/master/docs/diagrams/mc_v3.png)

## Postman documentation API REST

- [apartmentPredictorCRUD](https://documenter.getpostman.com/view/7473960/2sBXVeFs8L)

## JPA

### Summary

- [Getting Started :: Spring Data JPA](https://docs.spring.io/spring-data/jpa/reference/jpa/getting-started.html) / [Core concepts :: Spring Data JPA](https://docs.spring.io/spring-data/jpa/reference/repositories/core-concepts.html)

- [Spring Boot: Data &amp; DB – albertprofe wiki](https://albertprofe.dev/springboot/boot-concepts-data.html)

Spring Data’s mission is to provide a familiar and consistent, **Spring-based programming model for data access**.

> It makes it easy to use **data access** technologies, `relational` and `non-relational` databases, `map-reduce frameworks`, and `cloud-based` data services.

This is an **umbrella project which contains many subprojects** that are specific to a given database. The projects are developed by working together with many of the companies and developers that are behind these exciting technologies. 

## School-Apartment Distance Calculation

### Dijkstra's Algorithm?

- [Implementing Dijkstra&#x27;s Algorithm in Java: A Complete Guide - CodingTechRoom](https://codingtechroom.com/tutorial/java-implementing-dijkstras-algorithm-in-java)

> While Dijkstra's algorithm is excellent for finding shortest paths in weighted graphs, it's **not ideal** for calculating direct distances between schools and apartments using OpenStreetMap data.

#### Why Dijkstra's Isn't Suitable Here

**1. Overkill for Direct Distance:**

- Dijkstra's finds shortest paths through a network of roads/intersections
- For simple distance between two points, the Haversine formula is more efficient
- No need to traverse the entire street network when you just need straight-line or driving distance

**2. Performance Concerns:**

- Dijkstra's has O(V²) complexity without priority queues
- For thousands of apartment-school comparisons, this would be computationally expensive
- Each calculation would require loading and processing the entire road network graph

### Better Alternatives

**1. Haversine Formula:**

- Calculates great-circle distance between latitude/longitude points
- Fast and simple for straight-line distances
- Perfect for proximity filtering

**2. OpenStreetMap APIs:**

- Use routing APIs (like OSRM or GraphHopper) for actual driving distances
- More accurate for real-world travel scenarios
- Optimized for batch distance calculations

**3. Geospatial Libraries:**

- JTS (Java Topology Suite) for advanced geospatial operations
- PostGIS with PostgreSQL for database-level distance queries

### Best approach

todo

## Maven

> **Apache Maven** is a <mark>build tool for Java projects</mark>. Using a <mark>project object model </mark>(`POM`), Maven manages a project's compilation, testing, and documentation.

- [Maven](https://maven.apache.org/) / [Maven Repository](https://mvnrepository.com/)

### pom.xml

We added:

```xml
<dependency>  
 <groupId>org.springframework.boot</groupId>  
 <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

The `spring-boot-starter-validation` dependency provides Jakarta Bean Validation (JSR 380) annotations like `@NotNull`, `@Min`, and `@Max` used in the `School` entity. 

> It enables automatic validation of incoming request data and entity fields, ensuring data integrity and providing meaningful error messages when constraints are violated.

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
            <artifactId>spring-boot-starter-validation</artifactId>
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

- <mark>Spring Data JPA 4.0.0</mark>Endpoint: `PUT /api/apartment/assignSchoolsToApartment`→ `populatePropertyContracts(qty)` (with owners and apartments
