package com.example.apartment_predictor.model;

import java.util.UUID;

public class Duplex extends Apartment {

    private String balcony;
    private boolean elevator;


    public Duplex() {
        this.id = UUID.randomUUID().toString();
    }

    public Duplex(String balcony, boolean elevator, String airconditioning, String garden, int garageQty, String roofType) {
        this.id = UUID.randomUUID().toString();
        this.balcony = balcony;
        this.elevator = elevator;

    }

    @Override
    public Long calculatePrice() {
        double basePrice = area * 120 + (bedrooms * 8000);
        if (elevator) {
            basePrice *= 1.15;
        }
        //return basePrice * (1 + (area * 0.04));
        return 0L;
    }



    public String getBalcony() {
        return balcony;
    }

    public void setBalcony(String balcony) {
        this.balcony = balcony;
    }

    public boolean hasElevator() {
        return elevator;
    }

    public void setElevator(boolean elevator) {
        this.elevator = elevator;
    }



    @Override
    public String toString() {
        return "Duplex{" +
                "id='" + id + '\'' +
                ", balcony='" + balcony + '\'' +
                ", elevator='" + elevator + '\'' +

                '}';
    }
}
