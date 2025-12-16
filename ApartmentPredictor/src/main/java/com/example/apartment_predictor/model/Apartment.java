package com.example.apartment_predictor.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Apartment extends ResidentialProperty {

    @Id
    private int floorLevel;
    private boolean hasBalcony;

    // Constructor with all fields

    public Apartment(double area, String address, int locationRating, int numberOfBedrooms, int numberOfBathrooms, boolean hasGarden, boolean hasBalcony, int floorLevel) {
        super(area, address, locationRating, numberOfBedrooms, numberOfBathrooms, hasGarden);
        this.hasBalcony = hasBalcony;
        this.floorLevel = floorLevel;
    }

    public Apartment() {
        super();
    }


    // helpers


    // Getters and Setters
    public int getFloorLevel() {
        return floorLevel;
    }

    public void setFloorLevel(int floorLevel) {
        this.floorLevel = floorLevel;
    }

    public boolean hasBalcony() {
        return hasBalcony;
    }

    public void setHasBalcony(boolean hasBalcony) {
        this.hasBalcony = hasBalcony;
    }

    @Override
    public double calculatePrice() {
        return 750;
    }

    @Override
    public boolean requiresHomeownersAssociation() {
        return false;
    }
}
