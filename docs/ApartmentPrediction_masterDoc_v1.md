# ApartmentPredictor masterDoc v1

## Summary

### References

- Reference project: [Spring Boot: H2 DB and Thymeleaf – albertprofe wiki](https://albertprofe.dev/springboot/boot-what-create-th-h2.html)
- Microservices: https://spring.io/]
- Spring Boot is open-source: [GitHub - spring-projects/spring-boot: Spring Boot helps you to create Spring-powered, production-grade applications and services with absolute minimum fuss.](https://github.com/spring-projects/spring-boot)
- Spring Boot Guides / Academy: https://spring.io/guides / https://spring.academy/courses
- Quickstart: https://spring.io/quickstart

### Product Goal

> Use this small housing-price dataset to train a regularized regression model that handles multicollinearity, then expose predictions through a Spring Boot REST API and a React form UI where users input area, bedrooms, bathrooms, furnishing, and road proximity for instant price estimates.

We are defining a clear full-stack goal: implement RESTful CRUD endpoints in Spring Boot for Apartment and Post entities, then build React views to list, create, edit, delete, and assign posts to apartments with a simple, responsive UI.

From source:

> A simple yet challenging project, to predict the housing price based on certain factors like house area, bedrooms, furnished, nearness to mainroad, etc.
> 
> The dataset is small yet, it's complexity arises due to the fact that it has strong <mark>multicollinearity</mark>.

### Version goal

> We are defining a CRUD service in Spring Boot for Apartment, Owners, Reviewers and Post entities, then test them to list, create, edit, delete, and assign posts to apartments with <mark>JUnit</mark>.

## Create project

### Using the CLI

> The Spring Boot CLI (Command-Line Interface) can be installed manually by using <mark>SDKMAN</mark>! (the SDK Manager) or by using Homebrew or MacPorts if you are an OSX user. See [Installing the Spring Boot CLI](https://docs.spring.io/spring-boot/installing.html#getting-started.installing.cli) in the “Getting Started” section for comprehensive installation instructions.

- [Installing Spring Boot :: Spring Boot](https://docs.spring.io/spring-boot/installing.html#getting-started.installing.cli)

- [Using the CLI :: Spring Boot](https://docs.spring.io/spring-boot/cli/using-the-cli.html)

By CLI `Spring init`

```textile
[Tue Dec 02 11:09:02] albert@albert-VirtualBox:~
$ spring init --dependencies=web,data-jpa,h2,devtools --build=maven --java-version=21 --name=ApartmentPredictor --artifactId=apartment-predictor --groupId=com.example ApartmentPredictor
```

```text
spring init 
    --dependencies=web,data-jpa,h2,devtools
    --build=maven 
    --java-version=21 
    --name=ApartmentPredictor 
    --artifactId=apartment-predictor 
    --groupId=com.example ApartmentPredictor
```

### Spring Init

- https://start.spring.io/

### Project structure

```textile
[Tue Dec 02 17:12:58] albert@albert-VirtualBox:~/MyProjects/Sandbox/ApartmentPredictorProject/ApartmentPredictor (master)
$ tree 
.
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── apartment_predictor
│   │   │               ├── Apartment.java
│   │   │               ├── ApartmentPredictorApplication.java
│   │   │               ├── Owner.java
│   │   │               ├── Post.java
│   │   │               └── Reviewer.java
│   │   └── resources
│   │       ├── application.properties
│   │       ├── static
│   │       └── templates
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── apartment_predictor
│                       └── ApartmentPredictorApplicationTests.java
└── target
    ...

34 directories, 19 files
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
    private String hotwater;
    private String heating;
    private String airconditioning;
    private Integer parking;
    private String prefarea;
    private String furnishingstatus;
}
```

## pom.xml

```xml
todo
```

## Tech Stack

- IDE: IntelliJ IDEA 2025.1.3 (Community Edition)
  
  - [Descargar IntelliJ IDEA](https://www.jetbrains.com/es-es/idea/download/?section=linux)
  
  - With [Installing snap on Ubuntu | Snapcraft documentation](https://snapcraft.io/docs/installing-snap-on-ubuntu): `sudo snap install intellij-idea-community --classic`

- Java 21 (or 25, 17, 11, 8) <mark>open-jdk</mark>

- JUniit 3.8.1

- Create project by <mark>Sprint Init</mark>
  
  - Alternative: Maven Project: **`maven-archetype-quickstart` archetype**
    
    - https://maven.apache.org/
    
    - mvn --version
      Apache Maven 3.8.7
      Maven home: /usr/share/maven
      Java version: 21.0.8, vendor: Ubuntu, runtime: /usr/lib/jvm/java-21-openjdk-amd64
      Default locale: en_US, platform encoding: UTF-8
      OS name: "linux", version: "6.8.0-83-generic", arch: "amd64", family: "unix"
