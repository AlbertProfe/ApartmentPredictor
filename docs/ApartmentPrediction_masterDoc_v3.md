# ApartmentPredictor masterDoc v3

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

> We are defining a Rest Controller in Spring Boot for **Apartment, Review, Reviewer, Owner, PropiertyContract and School** entities, then test them to list, create, edit, delete, and assign reviews to apartments with <mark>Postman</mark> test tool.
> 
> We also are working in **Renovations** and **Person** as superclass for Owner and Reviewer.

## Project commits

- [Commits · AlbertProfe/ApartmentPredictor · GitHub](https://github.com/AlbertProfe/ApartmentPredictor/commits/master/)

### Project structure

```textile
[Sat Feb 21 09:13:33] albert@albert-VirtualBox:~/MyProjects/Sandbox/ApartmentPredictorProject/ApartmentPredictor/src/main/java/com/example/apartment_predictor (master)
$ tree
.
├── ApartmentPredictorApplication.java
├── controller
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
    // @OneToMany
    private List reviews;
    //@ManyToMany
    private List schools;
    //ManyToOne
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

![](https://raw.githubusercontent.com/AlbertProfe/ApartmentPredictor/refs/heads/master/docs/diagrams/mc_uml-junittest_v1.png)

## Postman documentation API REST

- [apartmentPredictorCRUD](https://documenter.getpostman.com/view/7473960/2sBXVeFs8L)

## Inherence

> The project uses a clean **single-table-per-class** inheritance strategy in JPA to model different types of people involved in the apartment review/ownership system.

Core Structure

```text
               Person
                 ↑
      ┌──────────┴──────────┐
      │                     │
   Reviewer              Owner
```

- **Person** is the **abstract base entity** (even though not marked abstract — it can still be used as base)
- **Owner** and **Reviewer** are concrete subclasses that extend **Person**

### JPA Configuration

```java
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person { ... }
```

```java
@Entity
public class Owner extends Person { ... }

@Entity
public class Reviewer extends Person { ... }
```

### Key Characteristics of `TABLE_PER_CLASS`

| Aspect               | Behavior in this setup                                  | Pros                                           | Cons (important to know)                                 |
| -------------------- | ------------------------------------------------------- | ---------------------------------------------- | -------------------------------------------------------- |
| Tables created       | Separate table for **Person**, **Owner**, **Reviewer**  | Clear separation, easy to query specific types | Duplicate columns (fullName, email, etc.) in every table |
| Columns              | All Person fields duplicated in Owner & Reviewer tables | No join needed to read a concrete type         | Wasted space if many common fields                       |
| Queries              | `SELECT * FROM Owner` → directly readable               | Simple for concrete-class queries              | Polymorphic queries (`Person p`) require UNION           |
| ID generation        | Each table has its own `id` column (UUID)               | No shared sequence issues                      | Cannot easily do `SELECT * FROM Person`                  |
| Polymorphism support | Limited — works, but slow (UNION under the hood)        | —                                              | Performance hit on polymorphic queries                   |

**Shared Fields (from Person)**

Every `Owner` and `Reviewer` row contains:

- `id` (UUID)
- `fullName`
- `email`
- `password` (⚠️ plain text — security concern in real app!)
- `birthDate`
- `isActive`

**Specific Fields**

| Entity   | Extra fields                                               | Business meaning                                                                    |
| -------- | ---------------------------------------------------------- | ----------------------------------------------------------------------------------- |
| Owner    | isBusiness, idLegalOwner, registrationDate, qtyDaysAsOwner | Company vs individual, legal ID, tenure tracking                                    |
| Reviewer | isBusiness, xAccount, webURL, qtyReviews                   | Professional reviewer? Just ocasional one?, Twitter/X handle, website, review count |

### Quick Summary

The model uses **InheritanceType.TABLE_PER_CLASS** to give **Owner** and **Reviewer** their own independent tables while inheriting common person attributes (`fullName`, `email`, `password`, `birthDate`, `isActive`) from the **Person** base class.

This is a good choice when:

- We rarely query across all persons polymorphically
- We want clean separation between owner and reviewer records
- We don't mind some column duplication

### Most common production alternatives

| Strategy            | When to prefer over TABLE_PER_CLASS                                               |
| ------------------- | --------------------------------------------------------------------------------- |
| **SINGLE_TABLE**    | High polymorphism, many shared queries, want best performance                     |
| **JOINED**          | Cleanest design, good normalization, still supports polymorphism                  |
| **TABLE_PER_CLASS** | You mostly work with concrete types, want simple table structure (current choice) |

> If this application grows (e.g. adding Admin, Tenant, Agent roles), **JOINED** or **SINGLE_TABLE** usually becomes more maintainable.

## JPA

### Summary

- [Getting Started :: Spring Data JPA](https://docs.spring.io/spring-data/jpa/reference/jpa/getting-started.html) / [Core concepts :: Spring Data JPA](https://docs.spring.io/spring-data/jpa/reference/repositories/core-concepts.html)

- [Spring Boot: Data &amp; DB – albertprofe wiki](https://albertprofe.dev/springboot/boot-concepts-data.html)

Spring Data’s mission is to provide a familiar and consistent, **Spring-based programming model for data access**.

> It makes it easy to use **data access** technologies, `relational` and `non-relational` databases, `map-reduce frameworks`, and `cloud-based` data services.

This is an **umbrella project which contains many subprojects** that are specific to a given database. The projects are developed by working together with many of the companies and developers that are behind these exciting technologies.

### Relationships

- [JPA Relationships Guide](https://github.com/AlbertProfe/ApartmentPredictor/blob/master/docs/appends/JPA_Relationships_Guide.md)
- [JPA Relationships Implementation Rule](https://github.com/AlbertProfe/ApartmentPredictor/blob/master/docs/appends/JPA_RelationshipsImplementation_Rule.md)

### Many-to-Many Apartment and School

Many-to-many, **unidirectional**, Apartment is the **owning side**

- **Cardinality**
  - One [Apartment](cci:2://file:///home/albert/MyProjects/Sandbox/ApartmentPredictorProject/ApartmentPredictor/src/main/java/com/example/apartment_predictor/model/Apartment.java:7:0-12:23) can be linked to many `School`s.
  - One `School` can be linked to many [Apartment](cci:2://file:///home/albert/MyProjects/Sandbox/ApartmentPredictorProject/ApartmentPredictor/src/main/java/com/example/apartment_predictor/model/Apartment.java:7:0-12:23)s (in the database via the join table), even though the `School` entity does **not** have an `apartments` field (because it’s **unidirectional**).
- **Owning side**
  - [Apartment](cci:2://file:///home/albert/MyProjects/Sandbox/ApartmentPredictorProject/ApartmentPredictor/src/main/java/com/example/apartment_predictor/model/Apartment.java:7:0-12:23) is the owning side because it declares `@ManyToMany` + `@JoinTable`.
  - `School` is not aware of the relationship in Java (no `mappedBy` on the other side).

<mark>Join table is what stores the relationship</mark>

Our mapping:

```java
@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
@JoinTable(
        name = "APARTMENT_SCHOOL_JOIN_TABLE",
        joinColumns = @JoinColumn(name = "apartment_id"),
        inverseJoinColumns = @JoinColumn(name = "school_id")
)
private List<School> schools = new ArrayList<>();
```

This creates/uses a join table:

- **Table:** `APARTMENT_SCHOOL_JOIN_TABLE`
- **Columns:**
  - `apartment_id` → FK to [Apartment](cci:2://file:///home/albert/MyProjects/Sandbox/ApartmentPredictorProject/ApartmentPredictor/src/main/java/com/example/apartment_predictor/model/Apartment.java:7:0-12:23)
  - `school_id` → FK to `School`

Each row means: “this apartment is connected to this school”.

#### How it works in our API REST controller

- [assignSchoolToApartment](https://github.com/AlbertProfe/ApartmentPredictor/blob/master/docs/appends/REST-assignSchoolToApartment.md)

### One-to-Many Apartment, Review and Reviewer

Explanation of the **relationships** between **Apartment**, **Review**, and **Reviewer** in our current domain model.

| Entity    | Side                 | Relationship Type      | Mapped By / Join Column              | Cascade | Fetch   | Meaning / Business Rule                                   |
| --------- | -------------------- | ---------------------- | ------------------------------------ | ------- | ------- | --------------------------------------------------------- |
| Apartment | owning side (parent) | **@OneToMany**         | `mappedBy = "apartment"`             | `ALL`   | `EAGER` | One apartment can have **many reviews**                   |
| Review    | inverse side (child) | **@ManyToOne**         | `@JoinColumn(name = "apartment_fk")` | `ALL`   | `EAGER` | Every review **belongs to exactly one** apartment         |
| Review    | inverse side (child) | **@ManyToOne**         | `@JoinColumn(name = "reviewer_fk")`  | `ALL`   | `EAGER` | Every review **was written by exactly one** reviewer      |
| Reviewer  | owning side (parent) | **implicit OneToMany** | (no collection in Reviewer)          | —       | —       | One reviewer can write **many reviews** (but no List<Revi |

```
Reviewer  1 ────★ n  Review n  ★───── 1  Apartment
   │                   │
   │ writes a Review   │ belongs to Reviewer
   └───────────────────┘
```

- **One reviewer** → can write **many reviews**

- **One review** → is written by **exactly one reviewer**

- **One review** → belongs to **exactly one apartment**

- **One apartment** → can have **many reviews**

This is a classic **many-to-one / one-to-many bidirectional** pattern from the **Review** perspective, plus a **one-to-many unidirectional** link from **Apartment → Reviews**.

#### Important observations

1. **Bidirectional Apartment ↔ Review**  
   
   - Apartment owns the relationship (`@OneToMany(mappedBy = "apartment")`)
   
   - Review has the foreign key (`@ManyToOne` + `@JoinColumn`)
   
   - We have helper methods in Apartment:
     
     ```java
     public void addReview(Review review) {
         reviews.add(review);
         review.setApartment(this);
     }
     ```

2. **Unidirectional Reviewer → Review**  
   
   - Reviewer **does not** have a `List<Review> reviews` collection  
   
   - Therefore the relationship is **unidirectional** from Review → Reviewer  
   
   - We **can** still query all reviews of a reviewer via JPQL / Spring Data:
     
     ```java
     SELECT r FROM Review r WHERE r.reviewer.id = :reviewerId
     ```

3. **Cascade = ALL everywhere**  
   
   - Very aggressive → saving / deleting an Apartment will also save / delete all its Reviews  
   - Saving a Review will also cascade to its Reviewer and Apartment (risky in many cases)  
   - **Recommendation for production**: usually use `CascadeType.PERSIST,MERGE` or even just `PERSIST` on `@ManyToOne` sides, and be more careful with `REMOVE`.

4. **Fetch = EAGER on almost everything**  
   
   - Every time you load an Apartment → all Reviews + all Reviewers + all Schools are loaded  
   - Every time you load a Review → its Apartment + its Reviewer are loaded  
     → Can cause **severe performance problems** (N+1 → huge joins or many selects)  
   - **Strong recommendation**: change almost all to `FetchType.LAZY` and use `@EntityGraph`, `JOIN FETCH` or Spring Data projections when you really need the data.

#### How it works in our API REST controller

- [assignReviewToApartment](https://github.com/AlbertProfe/ApartmentPredictor/blob/master/docs/appends/REST-assignReviewToApartment.md)

### Many-to-Many Apartment and Owner: bridge entity

In real estate / property management systems, the relationship between **owners** and **apartments** is usually **many-to-many** with additional contract data:

- One **Owner** can own **multiple apartments** (over time or simultaneously)
- One **Apartment** can have **multiple owners** (co-ownership, historical owners, changing ownership)
- The link carries extra information → contract date, value, document URL, active status, type → therefore **cannot** use plain `@ManyToMany` + `@JoinTable` (no extra columns possible)

**Standard JPA solution** = treat `PropertyContract` as an **associative / bridge / join entity** and model it with **two @ManyToOne** relationships:

```text
Owner           1 ──────── n   PropertyContract   n ──────── 1   Apartment
   │                                 │
   │ owns via contract               │ covers / belongs to
   └─────────────────────────────────┘
```

This turns the logical **M:N** into two **1:N** relationships:

- **Owner → PropertyContract** : **@OneToMany** (one owner can have many contracts)
- **PropertyContract → Owner** : **@ManyToOne** (each contract has exactly one current/legal owner)
- **Apartment → PropertyContract** : **@OneToMany** (one apartment can be covered by many historical/current contracts)
- **PropertyContract → Apartment** : **@ManyToOne** (each contract concerns exactly one apartment)

`PropertyContract` <mark>annotations</mark> as **bridge entity** (and owner side), and `Owner`, `Apartment` as inverse side:

```java
@Entity
public class PropertyContract {

    @Id
    private String id = UUID.randomUUID().toString();

    // ... existing fields ...

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    // optional: helper constructors / methods
}
```

```java
@Entity
public class Owner extends Person {

    // ... existing fields ...

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PropertyContract> contracts = new ArrayList<>();

    // helper
    public void addContract(PropertyContract contract) {
        contracts.add(contract);
        contract.setOwner(this);
    }
}
```

```java
@Entity
public class Apartment {

    // ... existing fields ...

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PropertyContract> contracts = new ArrayList<>();

    // helper
    public void addContract(PropertyContract contract) {
        contracts.add(contract);
        contract.setApartment(this);
    }
}
```

**Apartment ↔ Owner** is modeled as a **many-to-many** relationship **via the bridge entity PropertyContract**, which acts as an associative entity carrying contract-specific attributes (date, value, document, etc.).

- Each `PropertyContract` links **exactly one Owner** to **exactly one Apartment**
- This allows historical ownership, co-ownership, contract renewals, etc.
- Use **@OneToMany** on Owner and Apartment sides (bidirectional)
- Use **@ManyToOne** on PropertyContract (owning side of the foreign keys)

Summary table

| Aspect                                     | Bridge entity                                    |
| ------------------------------------------ | ------------------------------------------------ |
| Relationship type                          | Many-to-many via join entity, `PropertyContract` |
| Extra fields on link                       | Yes – stored in `PropertyContract`               |
| JPA annotations needed                     | @OneToMany + @ManyToOne ×2                       |
| Can query "all contracts of owner"         | Yes – `owner.getContracts()`                     |
| Can query "ownership history of apartment" | Yes – `apartment.getContracts()`                 |
| Cascade / orphanRemoval                    | Recommended on @OneToMany sides                  |

> This is the **standard, most flexible** way to handle many-to-many relationships that need additional attributes in JPA/Hibernate/Spring Data.

#### How it works in our API REST controller

- [assignOwnerApartmentToContract](https://github.com/AlbertProfe/ApartmentPredictor/blob/master/docs/appends/REST-assignOwnerApartmentToContract.md)

## Orchestrator: Populate All

> The `PopulateDB` class follows a **step-by-step orchestrator pattern** inside the central method `populateAll(int qty)`.

The current implementation structures data population in a clear, <mark>sequential pipeline</mark>:

1. Create base entities without relationships  
   → `populatePlainApartments(qty)` / → `populateReviewers(qty)`  
   → `populateSchools(qty)` / → `populateOwners(qty)`

2. Create relationships / assign child entities  
   → `assignSchoolsToApartments(...)`

3. Create next layer of entities  
   → `populatePropertyContracts(qty)` (with owners and apartments)  
   → `createPlainReviews(qty)` (reviews without owners yet) / → `populatePropertyContracts(qty)` (with owners and apartments)

4. Wire relationships in multiple steps  
   → `assignReviewersToReviews(...)`  
   → `assignReviewsToApartments(...)`

**Key characteristics of the pattern used here:**

- **Linear orchestration** — one master method calls smaller, single-responsibility methods in strict order
- **Progressive enrichment** — start with "plain" entities → gradually add relationships
- **Immutability avoidance** — entities are created, saved early, then updated multiple times (typical for JPA)
- **Debuggability** — lots of console logging after each important step
- **Return lists** — most helper methods return the list of persisted objects (good practice, even if not fully used yet)
- **Randomized test data** — ThreadLocalRandom + fixed arrays of realistic values

Orchestrator skeleton:

```java
public int populateAll(int qty) {

        // 1 populate Apartments > List
        List<Apartment> plainApartments = populatePlainApartments(qty);
        // 2 populate Schools > List
        List<School> schools = populateSchools(qty);
        // 3 assignSchoolsToApartments
        List<Apartment> plainApartmentsWithSchools =
                assignSchoolsToApartments(plainApartments, schools);

        // 4 populate Reviewers > List
        List<Reviewer> reviewers = populateReviewers(qty);
        // 5 create Reviews (very general description, valid for all apartments) and assign Reviewers
        // DO NOT SAVE to db!
        List<Review> plainReviews = createPlainReviews(qty);
        // 6 assign Reviewers to Reviews
        List<Review> reviews = assignReviewersToReviews(reviewers, plainReviews);
        // 7 assign Reviews to Apartments
        List<Apartment> plainApartmentsWithSchoolsAndReviews =
                assignReviewsToApartments(reviews, plainApartmentsWithSchools);

        // 8 populate Owners
        List<Owner> owners = populateOwners(qty);
        // 9 populate PropertyContracts assign Owners and Apartments
        List<PropertyContract> plainPropertyContractsAssigned =
                createAndAssignPropertyContracts(qty, owners, plainApartmentsWithSchoolsAndReviews);
        // 10 check and return qty of created objects
        // todo

}
```

This creates a very readable, maintainable "story" of how the test database is built — exactly the purpose of a good orchestrator / seeder class.

#### How schools get created (PopulateDB)

File: `src/main/java/com/example/apartment_predictor/utils/PopulateDB.java`

- `PopulateDB` creates `School` entities in the database first.
- Later, the controller assigns existing schools to an apartment by referencing their ids.
- Because the relationship is managed from the Apartment side, you only need to save/update the apartment to persist the join table links.

Notes about `cascade = CascadeType.ALL`

- With `CascadeType.ALL`, saving an Apartment can also persist `School` objects **if they are new/transient**.
- In your use case (schools already exist and you load them from SchoolRepository), cascade isn’t strictly required for the join table update, but it won’t break things as long as you don’t accidentally attach brand-new `School` instances with duplicate ids.

#### H2 Database tables

![](https://raw.githubusercontent.com/AlbertProfe/ApartmentPredictor/refs/heads/master/docs/diagrams/schoolsAssignToApartment-Flow/school-db.png)

![](https://raw.githubusercontent.com/AlbertProfe/ApartmentPredictor/refs/heads/master/docs/diagrams/schoolsAssignToApartment-Flow/apartment-schools-db.png)

![](https://raw.githubusercontent.com/AlbertProfe/ApartmentPredictor/refs/heads/master/docs/diagrams/schoolsAssignToApartment-Flow/apartment-db.png)

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

**Java Faker Code Method**

Add DataFaker dependency, inject `Faker` bean, and use `@PostConstruct` or `CommandLineRunner` to populate repositories. Generate realistic names, emails, dates, and UUIDs matching your `@Builder` entities: 

- `Conference.builder().title(faker.book().title()).build()`. Ideal for dynamic volumes.[[github](https://github.com/datafaker-net/datafaker)]​

**REST API Method**

Create `@RestController` endpoint `/api/fake-data` returning `List<Conference>`.

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
