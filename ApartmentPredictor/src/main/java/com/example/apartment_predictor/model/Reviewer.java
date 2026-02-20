package com.example.apartment_predictor.model;

import jakarta.persistence.Entity;
import java.time.LocalDate;


@Entity
public class Reviewer extends Person {

    private boolean isBusiness;
    private String xAccount;
    private String webURL;
    private int qtyReviews;


    // Default constructor
    public Reviewer() {}

    // Constructor delegating to Person
    public Reviewer(String fullName, String email, String password, LocalDate birthDate, boolean isActive,
                    boolean isBusiness, String xAccount, String webURL, int qtyReviews) {
        super(fullName, email, password, birthDate, isActive);
        this.isBusiness = isBusiness;
        this.xAccount = xAccount;
        this.webURL = webURL;
        this.qtyReviews = qtyReviews;

    }

    public boolean isBusiness() {
        return isBusiness;
    }

    public void setBusiness(boolean business) {
        isBusiness = business;
    }

    public String getxAccount() {
        return xAccount;
    }

    public void setxAccount(String xAccount) {
        this.xAccount = xAccount;
    }

    public String getWebURL() {
        return webURL;
    }

    public void setWebURL(String webURL) {
        this.webURL = webURL;
    }

    public int getQtyReviews() {
        return qtyReviews;
    }

    public void setQtyReviews(int qtyReviews) {
        this.qtyReviews = qtyReviews;
    }

    @Override
    public String toString() {
        return "Reviewer{" +
                "id='" + super.getId() + '\'' +
                ", name='" + super.getFullName() + '\'' +
                ", email='" + super.getEmail() + '\'' +
                ", isActive=" + super.isActive() +
                "isBusiness=" + isBusiness +
                ", xAccount='" + xAccount + '\'' +
                ", webURL='" + webURL + '\'' +
                ", qtyReviews=" + qtyReviews +
                '}';
    }
}

