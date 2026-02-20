package com.example.apartment_predictor.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class PropertyContract {

    @Id
    private String id;
    private String urlContractPropertyDocument;
    private LocalDate contractDate;
    private long valuePropertyContract;
    private String typeProperty;
    private String address;
    private boolean isActive;

    //private List<Owner> owners;
    //private List<Apartment> apartments;

    public PropertyContract() {
        this.id = UUID.randomUUID().toString();
    }

    public PropertyContract(String urlContractPropertyDocument, LocalDate contractDate, long valuePropertyContract, String typeProperty, String address, boolean isActive) {
        this.id = UUID.randomUUID().toString();
        this.urlContractPropertyDocument = urlContractPropertyDocument;
        this.contractDate = contractDate;
        this.valuePropertyContract = valuePropertyContract;
        this.typeProperty = typeProperty;
        this.address = address;
        this.isActive = isActive;
        //this.owners = new ArrayList<Owner>() ;
        //this.apartments = new ArrayList<Apartment>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrlContractPropertyDocument() {
        return urlContractPropertyDocument;
    }

    public void setUrlContractPropertyDocument(String urlContractPropertyDocument) {
        this.urlContractPropertyDocument = urlContractPropertyDocument;
    }

    public LocalDate getContractDate() {
        return contractDate;
    }

    public void setContractDate(LocalDate contractDate) {
        this.contractDate = contractDate;
    }

    public long getValuePropertyContract() {
        return valuePropertyContract;
    }

    public void setValuePropertyContract(long valuePropertyContract) {
        this.valuePropertyContract = valuePropertyContract;
    }

    public String getTypeProperty() {
        return typeProperty;
    }

    public void setTypeProperty(String typeProperty) {
        this.typeProperty = typeProperty;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

   /* public List<Owner> getOwners() {
        return owners;
    }

    public void setOwners(List<Owner> owners) {
        this.owners = owners;
    }

    public List<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(List<Apartment> apartments) {
        this.apartments = apartments;
    }*/

    @Override
    public String toString() {
        return "PropertyContract{" +
                "id='" + id + '\'' +
                ", urlContractPropertyDocument='" + urlContractPropertyDocument + '\'' +
                ", contractDate=" + contractDate +
                ", valuePropertyContract=" + valuePropertyContract +
                ", typeProperty='" + typeProperty + '\'' +
                ", address='" + address + '\'' +
                ", isActive=" + isActive +
                //", owners=" + owners +
                //", apartments=" + apartments +
                '}';
    }
}