package com.example.apartment_predictor.model;

import java.util.UUID;

public class House extends Apartment {

    private int garageQty;
    private String roofType;
    private String garden;

    public House() {
        this.id = UUID.randomUUID().toString();
    }

    public House(int garageQty, String roofType, String garden) {
        this.id = UUID.randomUUID().toString();
        this.garageQty = garageQty;
        this.roofType = roofType;
        this.garden = garden;
    }

    @Override
    public Long calculatePrice() {
        double basePrice = area * 120 + (bedrooms * 8000);
        if (garageQty > 0) {
            basePrice += 25000;
        }
        //return basePrice * (1 + (area * 0.04));
        return 0L;
    }

    public int getGarageQty() {
        return garageQty;
    }

    public void setGarageQty(int garageQty) {
        this.garageQty = garageQty;
    }

    public String getRoofType() {
        return roofType;
    }

    public void setRoofType(String roofType) {
        this.roofType = roofType;
    }

    public String getGarden() {
        return garden;
    }

    public void setGarden(String garden) {
        this.garden = garden;
    }

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", garageQty=" + garageQty +
                ", roofType='" + roofType + '\'' +
                ", garden='" + garden + '\'' +
                '}';
    }
}
