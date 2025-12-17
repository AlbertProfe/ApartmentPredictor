/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.apartment_predictor.model;

/**
 *
 * @author Alumne_mati1
 */
abstract public class Property {
    protected double area;
    protected String address;
    protected int locationRating;

    public Property(double area, String address, int locationRating) {
        this.area = area;
        this.address = address;
        this.locationRating = locationRating;
    }

    public Property() {

    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getLocationRating() {
        return locationRating;
    }

    public void setLocationRating(int locationRating) {
        this.locationRating = locationRating;
    }

    abstract public double calculatePrice();
}
