# ApartmentPredictor masterDoc v5

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

> The Apartment <mark>Filter Specification</mark> version 5.0 establishes a comprehensive filtering framework for apartment listings using `Spring Data JPA Specifications.` 

This implementation provides flexible,**type-safe querying** capabilities with multiple filter criteria including price ranges, area requirements, room counts, amenities, and location preferences. 

The `API` supports partial text matching for furnishing status and exact `boolean` filtering for yes/no fields, enabling precise apartment discovery. `Response` `headers` deliver valuable metadata including total database count and filtered result size, empowering clients with dataset insights. 

The **specification** prioritizes performance through single-query execution and lazy loading while maintaining clean `REST API` principles with standardized versioning and comprehensive error handling.

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

<mark>New classes:</mark>

```textile
.
├── ApartmentPredictorApplication.java
├── controller
│   ...
│   ├── ApartmentFilterRestController.java
│   ...
│   └── ...
├── model
│   ...
├── repository
│   ├── ApartmentRepository.java
│   ├── ApartmentSpecification.java
│   ...
├── service
│  
└── utils
    ...
    └── ...
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

Updated at `Queries` tab:

- [apartmentPredictorCRUD](https://documenter.getpostman.com/view/7473960/2sBXVeFs8L)

## JPA

### Summary

- [Getting Started :: Spring Data JPA](https://docs.spring.io/spring-data/jpa/reference/jpa/getting-started.html) / [Core concepts :: Spring Data JPA](https://docs.spring.io/spring-data/jpa/reference/repositories/core-concepts.html)

- [Spring Boot: Data &amp; DB – albertprofe wiki](https://albertprofe.dev/springboot/boot-concepts-data.html)

Spring Data’s mission is to provide a familiar and consistent, **Spring-based programming model for data access**.

> It makes it easy to use **data access** technologies, `relational` and `non-relational` databases, `map-reduce frameworks`, and `cloud-based` data services.

This is an **umbrella project which contains many subprojects** that are specific to a given database. The projects are developed by working together with many of the companies and developers that are behind these exciting technologies.

### Plain Apartment Specification

Specification

- [JPA-specification-1](https://github.com/AlbertProfe/ApartmentPredictor/blob/master/docs/appends/JPA-specification-1.md) / [JPA-specification-2](https://github.com/AlbertProfe/ApartmentPredictor/blob/master/docs/appends/JPA-specification-2.md) / [JPA-specification-Apartment Plain](https://github.com/AlbertProfe/ApartmentPredictor/blob/master/docs/appends/JPA-specification-Apartment.md)

> The Apartment Filter API provides flexible filtering capabilities for apartment listings using Spring Data JPA Specifications. This allows clients to filter apartments based on multiple criteria in a single request.  

<mark>Endpoint</mark>

![]()

```jsx
GET /api/v1/apartment/filter  
```

<mark>Request Parameters</mark>

All parameters are optional. If no parameters are provided, all apartments will be returned.  

| Parameter          | Type    | Description                                   | Example     |
| ------------------ | ------- | --------------------------------------------- | ----------- |
| `maxPrice`         | Long    | Maximum price (≤)                             | `10000000`  |
| `minArea`          | Integer | Minimum area in sq ft/m² (≥)                  | `1000`      |
| `minBedrooms`      | Integer | Minimum number of bedrooms (≥)                | `2`         |
| `minBathrooms`     | Integer | Minimum number of bathrooms (≥)               | `1`         |
| `minParking`       | Integer | Minimum parking spaces (≥)                    | `1`         |
| `furnishingStatus` | String  | Furnishing status (partial, case-insensitive) | `furnished` |
| `mainroad`         | Boolean | Located on main road (yes/no)                 | `true`      |
| `guestroom`        | Boolean | Has guest room (yes/no)                       | `false`     |
| `basement`         | Boolean | Has basement (yes/no)                         | `true`      |
| `hotwaterheating`  | Boolean | Has hot water heating (yes/no)                | `true`      |
| `airconditioning`  | Boolean | Has air conditioning (yes/no)                 | `true`      |
| `prefarea`         | Boolean | Located in preferred area (yes/no)            | `true`      |

<mark>Response Headers</mark>

The API returns metadata in response headers:  

| Header                | Description                         | Example                         |
| --------------------- | ----------------------------------- | ------------------------------- |
| `Status`              | Operation status                    | `filterApartments success`      |
| `version`             | API version                         | `1.0 Api Rest Apartment Object` |
| `active`              | API status                          | `true`                          |
| `author`              | API author                          | `Albert`                        |
| `total-apartments`    | Total apartments in database        | `545`                           |
| `filtered-apartments` | Apartments matching filter criteria | `23`                            |

<mark>Response Body</mark>

Returns a JSON array of Apartment objects that match the filter criteria.  

```json
 [
  {
    "id": "60a87c58-275d-4616-a550-476e9709b2ed",
    "price": 8500000,
    "area": 1200,
    "bedrooms": 2,
    "bathrooms": 2,
    "stories": 1,
    "mainroad": "yes",
    "guestroom": "no",
    "basement": "yes",
    "hotwaterheating": "yes",
    "airconditioning": "yes",
    "parking": 1,
    "prefarea": "yes",
    "furnishingstatus": "furnished"
    // other fields.. 
  }
]
```

### Example Requests

**Filter by Maximum Price**

```jsx
GET /api/v1/apartment/filter?maxPrice=10000000  
```

**Filter by Multiple Criteria**

```jsx
GET /api/v1/apartment/filter?maxPrice=15000000&minBedrooms=2&airconditioning=true&prefarea=true  
```

**Filter by Furnishing Status**

```jsx
GET /api/v1/apartment/filter?furnishingStatus=furnished  
```

**Complex Filter**

```jsx
GET /api/v1/apartment/filter?maxPrice=20000000&minArea=1000&minBedrooms=3&minBathrooms=2&furnishingStatus=semi&mainroad=true&airconditioning=true  
```

### Implementation Details

<mark>ApartmentSpecification</mark>

The filtering logic is implemented using `Spring Data JPA Specifications:  `

- **Price**: `≤ maxPrice` (if provided)  
- **Area**: `≥ minArea` (if provided)  
- **Bedrooms/Bathrooms/Parking**: `≥ minimum` (if provided)  
- **Furnishing Status**: Partial match, case-insensitive  
- **Boolean Fields**: Exact match against "yes"/"no" strings  

<mark>ApartmentFilterRestController</mark>

The `REST` controller:  

- Accepts optional query parameters  
- Creates Specification using ApartmentSpecification.filterBy()  
- Executes query using JpaSpecificationExecutor  
- Returns filtered results with metadata headers  

### Usage Examples

#### JavaScript/Fetch

```javascript
const response = await fetch('/api/v1/apartment/filter?maxPrice=10000000&minBedrooms=2');  
const apartments = await response.json();  
console.log('Total apartments:', response.headers.get('total-apartments'));  
console.log('Filtered apartments:', response.headers.get('filtered-apartments'));  
```

#### cURL

```bash
curl -X GET "http://localhost:8080/api/v1/apartment/filter?maxPrice=15000000&airconditioning=true" \  -H "Accept: application/json"```  
```

#### Postman

- Method: GET  
- URL: `http://localhost:8080/api/v1/apartment/filter`  
- Query Parameters: Add as needed  
- Headers: `Accept: application/json`

### Full Apartment Specification

```java
package com.example.apartment_predictor.repository;

import com.example.apartment_predictor.model.Apartment;
import com.example.apartment_predictor.model.School;
import com.example.apartment_predictor.model.Review;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.*;

import java.util.Objects;

public class ApartmentSpecification {

    public static Specification<Apartment> filterBy(
            Long maxPrice,              // ≤ this price
            Integer minArea,            // ≥ this area (sq ft / m²)
            Integer minBedrooms,
            Integer minBathrooms,
            Integer minParking,
            String furnishingStatus,    // "furnished", "semi-furnished", "unfurnished" (partial match)
            Boolean mainroad,           // yes/no
            Boolean guestroom,
            Boolean basement,
            Boolean hotwaterheating,
            Boolean airconditioning,
            Boolean prefarea,           // preferred area yes/no
            Integer minSchools,         // minimum number of schools
            String textOnReview,        // text to search in review title or content
            String textOnReviewTitle    // text to search in review title
    ) {
        return (Root<Apartment> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            Predicate p = cb.conjunction();   // starts as "true"

            // ─────────────────────────────────────────────
            // Price – most important filter
            // ─────────────────────────────────────────────
            if (maxPrice != null && maxPrice > 0) {
                p = cb.and(p, cb.lessThanOrEqualTo(root.get("price"), maxPrice));
            }

            // ─────────────────────────────────────────────
            // Area (size)
            // ─────────────────────────────────────────────
            if (minArea != null && minArea > 0) {
                p = cb.and(p, cb.greaterThanOrEqualTo(root.get("area"), minArea));
            }

            // ─────────────────────────────────────────────
            // Bedrooms & Bathrooms
            // ─────────────────────────────────────────────
            if (minBedrooms != null && minBedrooms > 0) {
                p = cb.and(p, cb.greaterThanOrEqualTo(root.get("bedrooms"), minBedrooms));
            }

            if (minBathrooms != null && minBathrooms > 0) {
                p = cb.and(p, cb.greaterThanOrEqualTo(root.get("bathrooms"), minBathrooms));
            }

            // ─────────────────────────────────────────────
            // Parking spaces
            // ─────────────────────────────────────────────
            if (minParking != null && minParking > 0) {
                p = cb.and(p, cb.greaterThanOrEqualTo(root.get("parking"), minParking));
            }

            // ─────────────────────────────────────────────
            // Furnishing status (partial / case-insensitive)
            // ─────────────────────────────────────────────
            if (isNotBlank(furnishingStatus)) {
                p = cb.and(p,
                    cb.like(
                        cb.lower(root.get("furnishingstatus")),
                        "%" + furnishingStatus.trim().toLowerCase() + "%"
                    )
                );
            }

            // ─────────────────────────────────────────────
            // Yes/No fields (Boolean-like strings: "yes"/"no")
            // ─────────────────────────────────────────────
            p = addYesNoFilter(p, cb, root, "mainroad", mainroad);
            p = addYesNoFilter(p, cb, root, "guestroom", guestroom);
            p = addYesNoFilter(p, cb, root, "basement", basement);
            p = addYesNoFilter(p, cb, root, "hotwaterheating", hotwaterheating);
            p = addYesNoFilter(p, cb, root, "airconditioning", airconditioning);
            p = addYesNoFilter(p, cb, root, "prefarea", prefarea);

            // ─────────────────────────────────────────────
            // Schools filter - minimum number of schools
            // ─────────────────────────────────────────────
            if (minSchools != null && minSchools > 0) {
                // Join the 'school' entity to the 'apartment' entity
                Join<Apartment, School> schoolJoin = root.join("schools", JoinType.LEFT);
                // Join<Apartment, School> schoolJoin = root.join("schools", JoinType.INNER);
                // Group by apartment and count schools
                query.groupBy(root.get("id"));
                query.having(cb.ge(cb.count(schoolJoin), minSchools));

            }

            // ─────────────────────────────────────────────
            // Review text filter - search in title
            // ─────────────────────────────────────────────
            if (isNotBlank(textOnReviewTitle)) {
                // Join the 'review' entity to the 'apartment' entity
                Join<Apartment, Review> reviewJoin = root.join("reviews", JoinType.INNER);
                String searchText = "%" + textOnReviewTitle.trim().toLowerCase() + "%";

                // Search only in title field
                p = cb.and(p, cb.like(cb.lower(reviewJoin.get("title")), searchText));
            }

            // ─────────────────────────────────────────────
            // Review text filter - search in title or content
            // ─────────────────────────────────────────────
            if (isNotBlank(textOnReview)) {
                // Join the 'review' entity to the 'apartment' entity
                Join<Apartment, Review> reviewJoin = root.join("reviews", JoinType.INNER);
                String searchText = "%" + textOnReview.trim().toLowerCase() + "%";

                // Search in both title and content fields
                Predicate titleMatch = cb.like(cb.lower(reviewJoin.get("title")), searchText);
                Predicate contentMatch = cb.like(cb.lower(reviewJoin.get("content")), searchText);
                Predicate reviewTextMatch = cb.or(titleMatch, contentMatch);

                p = cb.and(p, reviewTextMatch);
            }


            return p;
        };
    }

    // Small helper – makes yes/no filters cleaner
    private static Predicate addYesNoFilter(
            Predicate current,
            CriteriaBuilder cb,
            Root<Apartment> root,
            String fieldName,
            Boolean value) {

        if (value != null) {
            String expected = value ? "yes" : "no";
            return cb.and(current,
                cb.equal(
                    cb.lower(root.get(fieldName)),
                    expected
                )
            );
        }
        return current;
    }

    // Small utility – checks string is not null/empty/whitespace
    private static boolean isNotBlank(String s) {
        return s != null && !s.trim().isEmpty();
    }
}
```

> The [ApartmentSpecification](cci:2://file:///home/albert/MyProjects/Sandbox/ApartmentPredictorProject/ApartmentPredictor/src/main/java/com/example/apartment_predictor/repository/ApartmentSpecification.java:10:0-157:1) class **Spring Data JPA Specification** provides dynamic, criteria-based filtering for full Apartment entities.

**Filter Categories**:

- **Price/Area**: Maximum price, minimum area
- **Rooms**: Minimum bedrooms, bathrooms, parking spaces  
- **Amenities**: Boolean filters for mainroad, guestroom, basement, hotwaterheating, airconditioning, prefarea
- **Text Search**: Furnishing status (partial match), review title/content search
- **Related Entities**: <mark>Minimum number of schools, review text filtering</mark>

**Technical Implementation**:

- Uses `JPA Criteria API` for type-safe query building
- Implements `LEFT/INNER JOINs` for `School` and `Review` <mark>relationships</mark>
- Supports `GROUP BY` and `HAVING` clauses for `school` count filtering
- Case-insensitive text searches with `LIKE` operations
- Helper methods for `Boolean` string handling ("yes"/"no") and null checking

### Why Query Instead of Predicate for School Count

```java
if (minSchools != null && minSchools > 0) {
     // Join the 'school' entity to the 'apartment' entity
     Join<Apartment, School> schoolJoin = root.join("schools", JoinType.LEFT);
     // Join<Apartment, School> schoolJoin = root.join("schools", JoinType.INNER);
     // Group by apartment and count schools
     query.groupBy(root.get("id"));
     query.having(cb.ge(cb.count(schoolJoin), minSchools));
}
```

> The school count filtering uses <mark>direct query operations</mark> instead of `Predicate` because it requires **aggregation** (`counting related entities`), not simple field comparisons.

**Key Difference**:

- `Predicate` works for field-level conditions (price ≤ X, area ≥ Y)
- `groupBy()` and `having()` are needed for **aggregate functions** like COUNT()

When filtering by minimum number of `schools`, we must:

1. Join apartments with schools
2. Group results by apartment ID
3. Apply HAVING clause to filter groups by COUNT

This aggregation logic operates at the **query level**, not the predicate level. The Criteria API separates simple field conditions (Predicate) from aggregate operations (groupBy/having), making the query structure clearer and more aligned with SQL semantics.

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
