# ApartmentPredictor masterDoc v1



## Goal





## Create project

```textile
[Tue Dec 02 11:09:02] albert@albert-VirtualBox:~
$ spring init --dependencies=web,data-jpa,h2,devtools --build=maven --java-version=21 --name=ApartmentPredictor --artifactId=apartment-predictor --groupId=com.example ApartmentPredictor

```



## Data Model

- https://www.kaggle.com/datasets/yasserh/housing-prices-dataset/data

- 

```java
// java class
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



## Tech Stack
