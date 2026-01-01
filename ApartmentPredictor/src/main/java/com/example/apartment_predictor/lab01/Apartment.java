package com.example.apartment_predictor.lab01;

public class Apartment extends Property {

    protected int numberOfRooms;

    public Apartment(double area, int locationRating, int numberOfRooms) {
        super(area, locationRating);
        if (numberOfRooms <= 0) {
            throw new IllegalArgumentException("numberOfRooms must be > 0. Provided: " + numberOfRooms);
        }
        this.numberOfRooms = numberOfRooms;
    }

    @Override
    public String getPropertyType() {
        return "Apartment";
    }

    @Override
    public double calculatePrice() {
        double basePrice = area * 120 + (numberOfRooms * 8000);
        return basePrice * (1 + (locationRating * 0.04));
    }
}
