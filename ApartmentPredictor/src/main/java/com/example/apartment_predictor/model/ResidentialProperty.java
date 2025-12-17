/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.apartment_predictor.model;

/**
 *
 * @author Alumne_mati1
 */
abstract public class ResidentialProperty extends Property {
   protected int numberOfBedrooms;
   protected int numberOfBathrooms;
   protected boolean hasGarden;

    public ResidentialProperty(double area, String address, int locationRating, int numberOfBedrooms, int numberOfBathrooms, boolean hasGarden) {
        super(area, address, locationRating);
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.hasGarden = hasGarden;
    }

    public ResidentialProperty() {
        super();
    }

    public int getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public void setNumberOfBedrooms(int numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public int getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public void setNumberOfBathrooms(int numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public boolean isHasGarden() {
        return hasGarden;
    }

    public void setHasGarden(boolean hasGarden) {
        this.hasGarden = hasGarden;
    }

    abstract public boolean requiresHomeownersAssociation();
}
