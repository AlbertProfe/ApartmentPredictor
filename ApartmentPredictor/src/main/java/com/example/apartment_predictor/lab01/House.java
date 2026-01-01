package com.example.apartment_predictor.lab01;

public class House extends Apartment {

    private boolean hasGarage;

    public House(double area, int locationRating, int numberOfRooms, boolean hasGarage) {
        super(area, locationRating, numberOfRooms);
        this.hasGarage = hasGarage;
    }

    @Override
    public String getPropertyType() {
        return "Single-Family House";
    }

    @Override
    public double calculatePrice() {
        double basePrice = area * 180 + (numberOfRooms * 15000);
        if (hasGarage) {
            basePrice += 25000;
        }
        return basePrice * (1 + (locationRating * 0.06));
    }
}
