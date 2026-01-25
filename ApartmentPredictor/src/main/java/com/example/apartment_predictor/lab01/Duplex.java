package com.example.apartment_predictor.lab01;

public class Duplex extends Apartment {

    private boolean hasSeparateUtilities;

    public Duplex(double area, int locationRating, int numberOfRooms, boolean hasSeparateUtilities) {
        super(area, locationRating, numberOfRooms);
        this.hasSeparateUtilities = hasSeparateUtilities;
    }

    @Override
    public String getPropertyType() {
        return "Duplex (2 Units)";
    }

    @Override
    public double calculatePrice() {
        double basePrice = area * 220 + (numberOfRooms * 12000);
        if (hasSeparateUtilities) {
            basePrice *= 1.15;
        }
        return basePrice * (1 + (locationRating * 0.09));
    }
}
