package com.example.apartment_predictor.model;

abstract public class SingleFamilyHome extends ResidentialProperty{
    protected double lotSize;
    protected boolean hasGarage;
    protected boolean hasPool;
    protected boolean appropiateForPets;
    protected boolean appropiateForChildren;
    protected double hoaMonthlyFee;

    public SingleFamilyHome(double area, String address, int locationRating, int numberOfBedrooms, int numberOfBathrooms, boolean hasGarden, double lotSize, boolean hasGarage, boolean hasPool, boolean appropiateForPets, boolean appropiateForChildren, double hoaMonthlyFee) {
        super(area, address, locationRating, numberOfBedrooms, numberOfBathrooms, hasGarden);
        this.lotSize = lotSize;
        this.hasGarage = hasGarage;
        this.hasPool = hasPool;
        this.appropiateForPets = appropiateForPets;
        this.appropiateForChildren = appropiateForChildren;
        this.hoaMonthlyFee = hoaMonthlyFee;
    }

    public double getLotSize() {
        return lotSize;
    }

    public void setLotSize(double lotSize) {
        this.lotSize = lotSize;
    }

    public boolean hasGarage() {
        return hasGarage;
    }

    public void setHasGarage(boolean hasGarage) {
        this.hasGarage = hasGarage;
    }

    public boolean isHasPool() {
        return hasPool;
    }

    public void setHasPool(boolean hasPool) {
        this.hasPool = hasPool;
    }

    public boolean isAppropiateForPets() {
        return appropiateForPets;
    }

    public void setAppropiateForPets(boolean appropiateForPets) {
        this.appropiateForPets = appropiateForPets;
    }

    public boolean isAppropiateForChildren() {
        return appropiateForChildren;
    }

    public void setAppropiateForChildren(boolean appropiateForChildren) {
        this.appropiateForChildren = appropiateForChildren;
    }

    public double getHoaMonthlyFee() {
        return hoaMonthlyFee;
    }

    public void setHoaMonthlyFee(double hoaMonthlyFee) {
        this.hoaMonthlyFee = hoaMonthlyFee;
    }

    @Override
    public boolean requiresHomeownersAssociation() {
        return false;
    }
}
