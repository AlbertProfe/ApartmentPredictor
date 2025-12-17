package com.example.apartment_predictor.model;

public class Duplex extends SingleFamilyHome {

    private boolean hasSeparateUtilities;
    private int units;

    public Duplex(double area, String address, int locationRating, int numberOfBedrooms, int numberOfBathrooms, boolean hasGarden, double lotSize, boolean hasGarage, boolean hasPool, boolean appropiateForPets, boolean appropiateForChildren, double hoaMonthlyFee, boolean hasSeparateUtilities) {
        super(area, address, locationRating, numberOfBedrooms, numberOfBathrooms, hasGarden, lotSize, hasGarage, hasPool, appropiateForPets, appropiateForChildren, hoaMonthlyFee);
        this.hasSeparateUtilities = hasSeparateUtilities;
        this.units = 2;
    }

    public boolean isHasSeparateUtilities() {
        return hasSeparateUtilities;
    }

    public void setHasSeparateUtilities(boolean hasSeparateUtilities) {
        this.hasSeparateUtilities = hasSeparateUtilities;
    }

    public int getUnits() {
        return units;
    }

    @Override
    public double calculatePrice() {
        return numberOfBedrooms * 200 + hoaMonthlyFee;
    }

    @Override
    public boolean requiresHomeownersAssociation() {
        return appropiateForChildren || appropiateForPets || calculatePrice() < 1000 || area < 50;
    }
}
