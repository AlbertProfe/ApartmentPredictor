package com.example.apartment_predictor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import com.example.apartment_predictor.model.Apartment;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Review {

    @Id
    private String id;
    private String title;
    private String content;
    private int rating;
    private LocalDate reviewDate;
    @JsonIgnore
    @JoinColumn(name = "apartment_fk")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Apartment apartment;

    @JsonIgnore
    @JoinColumn(name = "reviewer_fk")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Reviewer reviewer;


    public Review() {
        this.id = UUID.randomUUID().toString();
    }

    public Review(String title, String content, Integer rating) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.content = content;
        this.rating = rating;
    }

    public Review(String title, String content, Integer rating, LocalDate reviewDate) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.content = content;
        this.rating = rating;
        this.reviewDate = reviewDate;
    }

    public String getId() {
        return id;
    }

    /*public void setId(String id) {
        this.id = id;
    }*/

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", rating=" + rating +
                '}';
    }
}
