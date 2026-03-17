# ApartmentPredictor masterDoc v4

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

### Product Goal

> Use this small housing-price dataset to train a regularized regression model that handles multicollinearity, then expose predictions through a Spring Boot REST API and a React form UI where users input area, bedrooms, bathrooms, furnishing, and road proximity for instant price estimates.

We are defining a clear full-stack goal: implement RESTful CRUD endpoints in Spring Boot for Apartment and Post entities, then build React views to list, create, edit, delete, and assign posts to apartments with a simple, responsive UI.

From source:

> A simple yet challenging project, to predict the housing price based on certain factors like house area, bedrooms, furnished, nearness to mainroad, etc.
> 
> The dataset is small yet, it's complexity arises due to the fact that it has strong <mark>multicollinearity</mark>.

### Version goal

<mark>Pagination Implementation for Apartment Listings</mark>

> Implement comprehensive **pagination** functionality for the Apartment entity to enhance performance and user experience when displaying apartment listings in the Apartment Predictor application.

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

![](https://raw.githubusercontent.com/AlbertProfe/ApartmentPredictor/refs/heads/master/docs/screenshots/postman-getPaginated.png)

- [apartmentPredictorCRUD](https://documenter.getpostman.com/view/7473960/2sBXVeFs8L)

## JPA

### Summary

- [Getting Started :: Spring Data JPA](https://docs.spring.io/spring-data/jpa/reference/jpa/getting-started.html) / [Core concepts :: Spring Data JPA](https://docs.spring.io/spring-data/jpa/reference/repositories/core-concepts.html)

- [Spring Boot: Data &amp; DB – albertprofe wiki](https://albertprofe.dev/springboot/boot-concepts-data.html)

Spring Data’s mission is to provide a familiar and consistent, **Spring-based programming model for data access**.

> It makes it easy to use **data access** technologies, `relational` and `non-relational` databases, `map-reduce frameworks`, and `cloud-based` data services.

This is an **umbrella project which contains many subprojects** that are specific to a given database. The projects are developed by working together with many of the companies and developers that are behind these exciting technologies.

### Lazy and Eager

- https://github.com/AlbertProfe/userBorrowBookFilter/blob/master/docs/userBorrowBook-filter-lazy-eager.md

### Cascade Delete

> Gaol to refactor: when an Apartment is deleted:

1. ✅ **School**: NOT deleted (only join table entries removed)

2. ✅ **Review**: DELETED (but `Reviewer` remains)

3. ✅ **PropertyContract**: DELETED (but `Owner` remains)

> The refactoring ensures that deleting an `Apartment` only removes its directly dependent entities while preserving shared entities that may be referenced by other apartments in the system.

<mark>Current State Analysis</mark> form *version 3*

**Current Relationships in Apartment.java:**

- **Reviews**: `@OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL, fetch = FetchType.EAGER)`
- **Schools**: `@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)` (Line 30)
- **Contracts**: `@OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL, orphanRemoval = true)`

**Current Issues:**

1. **Reviews**: Currently cascades ALL, which would delete Reviewer when Apartment is deleted
2. **Schools**: Currently cascades ALL, which would delete School when Apartment is deleted
3. **Contracts**: Currently cascades ALL, which would delete Owner when Apartment is deleted

<mark>Required Changes</mark>

#### 1. Reviews Relationship

**Current Problem**: `cascade = CascadeType.ALL` deletes Reviewer when Apartment is deleted **Solution**: Change to `cascade = CascadeType.REMOVE` to only delete Review entities

```java
@OneToMany(mappedBy = "apartment", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
private List<Review> reviews = new ArrayList<>();
```

#### 2. Schools Relationship

**Current Problem**: `cascade = CascadeType.ALL` deletes School when Apartment is deleted **Solution**: Remove cascade completely to prevent School deletion

```java
@ManyToMany(fetch = FetchType.EAGER)
@JoinTable(
    name = "APARTMENT_SCHOOL_JOIN_TABLE",
    joinColumns = @JoinColumn(name = "apartment_id"),
    inverseJoinColumns = @JoinColumn(name = "school_id")
)
private List<School> schools = new ArrayList<>();
```

#### 3. Contracts Relationship

**Current Problem**: `cascade = CascadeType.ALL` deletes Owner when Apartment is deleted **Solution**: Change to `cascade = CascadeType.REMOVE` to only delete PropertyContract entities

```java
@OneToMany(mappedBy = "apartment", cascade = CascadeType.REMOVE, orphanRemoval = true)
private List<PropertyContract> contracts = new ArrayList<>();
```

#### 4. Review Entity

**Current Problem**: Both relationships have `cascade = CascadeType.ALL` **Solution**: Remove cascade from both relationships to prevent cascading to Apartment and Reviewer

```java
@JoinColumn(name = "apartment_fk")
@ManyToOne(fetch = FetchType.EAGER)
private Apartment apartment;

@JoinColumn(name = "reviewer_fk") 
@ManyToOne(fetch = FetchType.EAGER)
private Reviewer reviewer;
```

<mark>Refactored</mark> `Apartment.java`

```java
@OneToMany(mappedBy = "apartment", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
private List<Review> reviews = new ArrayList<>();

@ManyToMany(fetch = FetchType.EAGER)
@JoinTable(
    name = "APARTMENT_SCHOOL_JOIN_TABLE",
    joinColumns = @JoinColumn(name = "apartment_id"),
    inverseJoinColumns = @JoinColumn(name = "school_id")
)
private List<School> schools = new ArrayList<>();

@OneToMany(mappedBy = "apartment", cascade = CascadeType.REMOVE, orphanRemoval = true)
private List<PropertyContract> contracts = new ArrayList<>();
```

## Pagination

-  [Paging and Sorting](https://docs.spring.io/spring-data/jpa/reference/repositories/query-methods-details.html#repositories.paging-and-sorting) /  [Pagination](https://github.com/AlbertProfe/userBorrowBookPagination/blob/master/docs/pagination.md) / [Pageable: repo](https://github.com/AlbertProfe/BooksPageable)

- [userBorrowBookPagination/docs](https://github.com/AlbertProfe/userBorrowBookPagination/tree/master/docs)

> `Pagination` implementation will transform the current apartment listing system from loading <mark>all records into an efficient, scalable solution</mark> that handles thousands of apartments while maintaining optimal performance.

Technical Requirements

- Integrate Spring Data JPA pagination using `Pageable` interface
- Modify repository methods to return `Page<Apartment>` instead of `List<Apartment>`
- Update REST controllers to accept pagination parameters (`page`, `size`, `sort`)
- Implement custom pagination queries for complex filtering scenarios (price range, area, bedrooms)
- Add pagination metadata to API responses including total elements, total pages, and current page info
- Create frontend pagination controls with navigation buttons and page number display
- Optimize database queries by limiting result sets and implementing proper indexing

### Core Components

- **Repository Layer:** Extended the [ApartmentRepository](cci:2://file:///home/albert/MyProjects/Sandbox/ApartmentPredictorProject/ApartmentPredictor/src/main/java/com/example/apartment_predictor/repository/ApartmentRepository.java:6:0-7:1) interface to implement both `CrudRepository` and `PagingAndSortingRepository`. 
  
  - This combination provides basic CRUD operations alongside pagination capabilities. The repository automatically gains access to [findAll(Pageable pageable)](cci:1://file:///home/albert/MyProjects/Sandbox/ApartmentPredictorProject/ApartmentPredictor/src/main/java/com/example/apartment_predictor/service/ApartmentService.java:19:4-21:5) method for paginated queries.

- **Service Layer:** Added [findPaginated(int pageNo, int pageSize)](cci:1://file:///home/albert/MyProjects/Sandbox/ApartmentPredictorProject/ApartmentPredictor/src/main/java/com/example/apartment_predictor/service/ApartmentService.java:83:4-85:5) method in [ApartmentService](cci:2://file:///home/albert/MyProjects/Sandbox/ApartmentPredictorProject/ApartmentPredictor/src/main/java/com/example/apartment_predictor/service/ApartmentService.java:13:0-90:1) that creates a `Pageable` object using `Pageable.ofSize(pageSize).withPage(pageNo)`. 
  
  - This method delegates to the repository's pagination method and returns a `Page<Apartment>` object containing both data and metadata.

- **Controller Layer:** Implemented <mark>REST endpoint</mark> `@GetMapping("/page")` with `@RequestParam int pageNo` to handle pagination requests. 
  
  - The `endpoint` returns `ResponseEntity<Page<Apartment>>` with custom HTTP headers including pagination metadata like page number, page size, and total object count.

### Key Features

The `Page` object provides comprehensive pagination information including:

- content list, 
- total elements, 
- total pages, 
- current page number, and whether the page has next/previous pages. 

Headers are added to responses with <mark>metadata</mark> (`pageNo`, `pageSize`, `totalObjects`) for <mark>client-side pagination controls.</mark>

### Code

Rest Controller, Service and Repository

```java
// Rest Controler

@GetMapping("/page")
public ResponseEntity<Page<Apartment>> getApartmentsPaginated(@RequestParam int pageNo) {
        final int PAGE_SIZE = 5;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Status", "getApartmentsPaginated executed");
        headers.add("version", "1.0 Api Rest Apartment Object");
        headers.add("active", "true");
        headers.add("author", "Albert");
        headers.add("pageSize", String.valueOf(PAGE_SIZE));
        headers.add("pageNo", String.valueOf(pageNo));


        Page<Apartment> apartments = apartmentService.findPaginated(pageNo, PAGE_SIZE);
        headers.add("totalObjects", String.valueOf(apartments.getTotalElements()));

        return ResponseEntity.ok().headers(headers).body(apartments);
    }

// service
public Page<Apartment> findPaginated(int pageNo, int pageSize) {
        return apartmentRepository.findAll(Pageable.ofSize(pageSize).withPage(pageNo));
    }

// repository

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.example.apartment_predictor.model.Apartment;

public interface ApartmentRepository extends CrudRepository<Apartment, String>, PagingAndSortingRepository<Apartment, String> {
}
```

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

- <mark>Spring Data JPA 4.0.0</mark>Endpoint: `PUT /api/apartment/assignSchoolsToApartment`→ `populatePropertyContracts(qty)` (with owners and apartments)
