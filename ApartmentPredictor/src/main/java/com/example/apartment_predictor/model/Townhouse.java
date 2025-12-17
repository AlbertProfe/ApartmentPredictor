package com.example.apartment_predictor.model;

public class Townhouse extends SingleFamilyHome{

    private boolean hasHOA;

    public Townhouse(double area, String address, int locationRating, int numberOfBedrooms, int numberOfBathrooms, boolean hasGarden, double lotSize, boolean hasGarage, boolean hasPool, boolean appropiateForPets, boolean appropiateForChildren, double hoaMonthlyFee, boolean hasHOA) {
        super(area, address, locationRating, numberOfBedrooms, numberOfBathrooms, hasGarden, lotSize, hasGarage, hasPool, appropiateForPets, appropiateForChildren, hoaMonthlyFee);
        this.hasHOA = hasHOA;
    }

    public boolean hasHOA() {
        return hasHOA;
    }

    public void setHasHOA(boolean hasHOA) {
        this.hasHOA = hasHOA;
    }

    @Override
    public double calculatePrice() {
        return numberOfBedrooms * 150 + hoaMonthlyFee;
    }

    @Override
    public boolean requiresHomeownersAssociation() {
        return hasHOA;
    }
}
