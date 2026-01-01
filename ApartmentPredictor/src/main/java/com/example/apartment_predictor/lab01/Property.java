package com.example.apartment_predictor.lab01;

public abstract class Property {

    protected double area;
    protected int locationRating;

    protected Property(double area, int locationRating) {
        if (area <= 0) {
            throw new IllegalArgumentException("area must be > 0. Provided: " + area);
        }
        if (locationRating < 0 || locationRating > 5) {
            throw new IllegalArgumentException("locationRating must be between 0 and 5. Provided: " + locationRating);
        }
        this.area = area;
        this.locationRating = locationRating;
    }

    // Lab Variant 1
    public abstract String getPropertyType();

    // Lo dejamos porque el enunciado base lo tenía (aunque no lo uses ahora)
    public abstract double calculatePrice();
}
