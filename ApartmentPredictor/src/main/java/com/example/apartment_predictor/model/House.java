package com.example.apartment_predictor.model;

public class House extends SingleFamilyHome{

    private boolean hasBasement;
    private double basementArea;

    public House(double area, String address, int locationRating, int numberOfBedrooms, int numberOfBathrooms, boolean hasGarden, double lotSize, boolean hasGarage, boolean hasPool, boolean appropiateForPets, boolean appropiateForChildren, double hoaMonthlyFee, boolean hasBasement, double basementArea) {
        super(area, address, locationRating, numberOfBedrooms, numberOfBathrooms, hasGarden, lotSize, hasGarage, hasPool, appropiateForPets, appropiateForChildren, hoaMonthlyFee);
        this.hasBasement = hasBasement;
        this.basementArea = basementArea;
    }

    public boolean isHasBasement() {
        return hasBasement;
    }

    public void setHasBasement(boolean hasBasement) {
        this.hasBasement = hasBasement;
    }

    public double getBasementArea() {
        return basementArea;
    }

    public void setBasementArea(double basementArea) {
        this.basementArea = basementArea;
    }

    @Override
    public double calculatePrice() {
        return numberOfBedrooms * 125 + hoaMonthlyFee + basementArea * 2;
    }

    @Override
    public boolean requiresHomeownersAssociation() {
        return appropiateForChildren || appropiateForPets || calculatePrice() < 1000 || area < 50 || basementArea > 20;
    }
}
