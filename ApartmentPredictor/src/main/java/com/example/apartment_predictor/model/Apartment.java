package com.example.apartment_predictor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
public class Apartment {

    @Id
    protected String id;
    private String name;
    protected Integer area;
    protected Integer bedrooms;
    private Integer bathrooms;
    private Integer stories;
    private boolean guestroom;
    private boolean basement;
    private boolean hotwaterheating;
    private boolean airconditioning;
    private boolean parking;
    private FurnishingStatus furnishingstatus;

    public Apartment(String id, String name, Integer area, Integer bedrooms, Integer bathrooms, Integer stories, boolean guestroom, boolean basement, boolean hotwaterheating, boolean airconditioning, boolean parking, FurnishingStatus furnishingstatus) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.stories = stories;
        this.guestroom = guestroom;
        this.basement = basement;
        this.hotwaterheating = hotwaterheating;
        this.airconditioning = airconditioning;
        this.parking = parking;
        this.furnishingstatus = furnishingstatus;
    }

    public Apartment() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return calculatePrice();
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

    public boolean isGuestroom() {
        return guestroom;
    }

    public void setGuestroom(boolean guestroom) {
        this.guestroom = guestroom;
    }

    public boolean isBasement() {
        return basement;
    }

    public void setBasement(boolean basement) {
        this.basement = basement;
    }

    public boolean isHotwaterheating() {
        return hotwaterheating;
    }

    public void setHotwaterheating(boolean hotwaterheating) {
        this.hotwaterheating = hotwaterheating;
    }

    public boolean isAirconditioning() {
        return airconditioning;
    }

    public void setAirconditioning(boolean airconditioning) {
        this.airconditioning = airconditioning;
    }

    public boolean isParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    public String getFurnishingstatus() {
        return furnishingstatus.getLiteral();
    }

    @JsonIgnore
    public FurnishingStatus getRawFurnishingstatus() {
        return furnishingstatus;
    }

    public void setFurnishingstatus(FurnishingStatus furnishingstatus) {
        this.furnishingstatus = furnishingstatus;
    }

    // Funciones adicionales
    public Long calculatePrice() {

        Long price = 0L;

        price += area * 1500;
        price = Math.round(price * (1 + (stories - 1) * 0.15)); // For each additional story, the price of each m2 gets multiplied.
        price += bedrooms * 7500;
        price += bathrooms * 5500;
        price += guestroom ? 10000 : 0;
        price += hotwaterheating ? 15000 : 0;
        price += airconditioning ? 20000 : 0;
        price += parking ? 30000 : 0;
        price += basement ? 45000 : 0;

        switch (furnishingstatus) {
            case PARTIALLY_FURNISHED ->  price += 3000;
            case FULLY_FURNISHED -> price += 8500;
            default -> price += 0;
        }

        // Simplified inflation simulation.
        // Applies a multiplier based on the amount of days that passed since 1970
        double multiplier;
        int daysPassed;
        Instant currentTime = Instant.now();

        daysPassed = (int) Math.floor((double) currentTime.getEpochSecond() / 86400);
        multiplier = 1 + daysPassed * 0.000075;

        return Math.round(price * multiplier);
    }
}
