package com.example.apartment_predictor.controller;

import com.example.apartment_predictor.model.*;
import com.example.apartment_predictor.repository.ReviewRepository;
import com.example.apartment_predictor.repository.ReviewerRepository;
import com.example.apartment_predictor.repository.SchoolRepository;
import com.example.apartment_predictor.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("api/v1/assign/apartment")
public class ApartmentAssignRestController {

    @Autowired
    ApartmentService apartmentService;

    @Autowired
    SchoolRepository schoolRepository;

    @Autowired
    ReviewerRepository reviewerRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @PutMapping("/schools")
    public ResponseEntity<Apartment> assignSchoolsToApartment(
            @RequestParam String apartmentId,
            @RequestParam List<String> schoolIds
    ) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Status", "assignSchoolsToApartment executed");
        headers.add("version", "1.0 Api Rest Apartment Object");
        headers.add("active", "true");
        headers.add("author", "Albert");

        Apartment apartment = apartmentService.findApartmentById(apartmentId);
        if (apartment == null) {
            headers.add("Status", "assignSchoolsToApartment failed: apartment not found");
            return ResponseEntity.
                    badRequest().headers(headers).
                    body(null);
        }

        if (schoolIds == null || schoolIds.isEmpty()) {
            headers.add("Status", "assignSchoolsToApartment failed: schoolIds is null or empty");
            apartment.setSchools(new ArrayList<>());
            return ResponseEntity.badRequest().headers(headers).body(null);
        }

        Iterable<School> found = schoolRepository.findAllById(schoolIds);
        List<School> schoolsFound = StreamSupport.stream(found.spliterator(), false)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (schoolsFound.size() != schoolIds.size()) {
            headers.add("Status", "assignSchoolsToApartment failed: schools not found");
            return ResponseEntity.badRequest().headers(headers).body(null);
        }

        apartment.addSchools(schoolsFound);
        headers.add("Status", "assignSchoolsToApartment success");
        Apartment apartmentSaved = apartmentService.updateApartment(apartment);
        return ResponseEntity.ok().headers(headers).body(apartmentSaved);
    }

    @PutMapping("/reviews")
    public ResponseEntity<Apartment> assignReviewToApartment(
            @RequestParam String apartmentId,
            @RequestParam String reviewerId,
            @RequestBody Review reviewData
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Status", "assignReviewToApartment executed");
        headers.add("version", "1.0 Api Rest Apartment Object");
        headers.add("active", "true");
        headers.add("author", "Albert");

        // Validate apartment exists
        Apartment apartment = apartmentService.findApartmentById(apartmentId);
        if (apartment == null) {
            headers.add("Status", "assignReviewToApartment failed: apartment not found");
            return ResponseEntity.badRequest().headers(headers).body(null);
        }

        // Validate reviewer exists
        Reviewer reviewer = reviewerRepository.findById(reviewerId).orElse(null);
        if (reviewer == null) {
            headers.add("Status", "assignReviewToApartment failed: reviewer not found");
            return ResponseEntity.badRequest().headers(headers).body(null);
        }

        // Validate review data
        if (reviewData == null) {
            headers.add("Status", "assignReviewToApartment failed: review data is null");
            return ResponseEntity.badRequest().headers(headers).body(null);
        }

        // Create and configure the review
        Review newReview = new Review();
        newReview.setTitle(reviewData.getTitle());
        newReview.setContent(reviewData.getContent());
        newReview.setRating(reviewData.getRating());
        newReview.setReviewDate(reviewData.getReviewDate() != null ? reviewData.getReviewDate() : LocalDate.now());
        newReview.setReviewer(reviewer);

        // Save the review
        reviewRepository.save(newReview);

        // Get the review from the database
        Review review = reviewRepository.findById(newReview.getId()).orElse(null);

        if (review == null) {
            headers.add("Status", "assignReviewToApartment failed: review not found");
            return ResponseEntity.badRequest().headers(headers).body(null);
        }

        // Add review to apartment (this also sets the apartment reference on the review)
        apartment.addReview(review);

        // Save the updated apartment
        Apartment apartmentSaved = apartmentService.updateApartment(apartment);
        headers.add("Status", "assignReviewToApartment success");

        return ResponseEntity.ok().headers(headers).body(apartmentSaved);
    }

    @PutMapping("/contractOwner")
    public ResponseEntity<Apartment> assignOwnerAndApartmentToContract(
            @RequestParam String apartmentId,
            @RequestParam String ownerId,
            @RequestBody PropertyContract propertyContractData
    ){
        // defensive programming: apartmentId, ownerId exists

        // create a new PropertyContract with id UUID with data from propertyContractData

        // get apartment by id, get owner by id

        // newPropertyContract.setOwner(owner);
        // newPropertyContract.setApartment(apartment);

        // save newPropertyContract

        // return apartment




        return null;
    }


}
