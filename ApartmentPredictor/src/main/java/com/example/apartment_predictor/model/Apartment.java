package com.example.apartment_predictor.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Apartment {

    @Id
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

    // Default constructor
    public Apartment() {}

    // Constructor with all fields
    public Apartment(Long price, Integer area, Integer bedrooms, Integer bathrooms, 
                    Integer stories, String mainroad, String guestroom, String basement,
                    String hotwater, String heating, String airconditioning, 
                    Integer parking, String prefarea, String furnishingstatus) {
        this.price = price;
        this.area = area;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.stories = stories;
        this.mainroad = mainroad;
        this.guestroom = guestroom;
        this.basement = basement;
        this.hotwater = hotwater;
        this.heating = heating;
        this.airconditioning = airconditioning;
        this.parking = parking;
        this.prefarea = prefarea;
        this.furnishingstatus = furnishingstatus;
    }



    // Getters and Setters
    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
    }

    public Integer getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(Integer bathrooms) {
        this.bathrooms = bathrooms;
    }

    public Integer getStories() {
        return stories;
    }

    public void setStories(Integer stories) {
        this.stories = stories;
    }

    public String getMainroad() {
        return mainroad;
    }

    public void setMainroad(String mainroad) {
        this.mainroad = mainroad;
    }

    public String getGuestroom() {
        return guestroom;
    }

    public void setGuestroom(String guestroom) {
        this.guestroom = guestroom;
    }

    public String getBasement() {
        return basement;
    }

    public void setBasement(String basement) {
        this.basement = basement;
    }

    public String getHotwater() {
        return hotwater;
    }

    public void setHotwater(String hotwater) {
        this.hotwater = hotwater;
    }

    public String getHeating() {
        return heating;
    }

    public void setHeating(String heating) {
        this.heating = heating;
    }

    public String getAirconditioning() {
        return airconditioning;
    }

    public void setAirconditioning(String airconditioning) {
        this.airconditioning = airconditioning;
    }

    public Integer getParking() {
        return parking;
    }

    public void setParking(Integer parking) {
        this.parking = parking;
    }

    public String getPrefarea() {
        return prefarea;
    }

    public void setPrefarea(String prefarea) {
        this.prefarea = prefarea;
    }

    public String getFurnishingstatus() {
        return furnishingstatus;
    }

    public void setFurnishingstatus(String furnishingstatus) {
        this.furnishingstatus = furnishingstatus;
    }

    public String getId() {
        return id;
    }

    /*public void setId(String id) {
        this.id = id;
    }*/

    @Override
    public String toString() {
        return "Apartment{" +
                "price=" + price +
                ", area=" + area +
                ", bedrooms=" + bedrooms +
                ", bathrooms=" + bathrooms +
                ", stories=" + stories +
                ", mainroad='" + mainroad + '\'' +
                ", guestroom='" + guestroom + '\'' +
                ", basement='" + basement + '\'' +
                ", hotwater='" + hotwater + '\'' +
                ", heating='" + heating + '\'' +
                ", airconditioning='" + airconditioning + '\'' +
                ", parking=" + parking +
                ", prefarea='" + prefarea + '\'' +
                ", furnishingstatus='" + furnishingstatus + '\'' +
                '}';
    }
}
