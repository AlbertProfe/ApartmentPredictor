package com.example.apartment_predictor.controller;

import com.example.apartment_predictor.model.Apartment;
import com.example.apartment_predictor.repository.ApartmentRepository;
import com.example.apartment_predictor.repository.ApartmentSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/apartment")
public class ApartmentFilterRestController {

    @Autowired
    private ApartmentRepository apartmentRepository;

    @GetMapping("/filter")
    public ResponseEntity<List<Apartment>> filterApartments(
            @RequestParam(required = false) Long maxPrice,
            @RequestParam(required = false) Integer minArea,
            @RequestParam(required = false) Integer minBedrooms,
            @RequestParam(required = false) Integer minBathrooms,
            @RequestParam(required = false) Integer minParking,
            @RequestParam(required = false) String furnishingStatus,
            @RequestParam(required = false) Boolean mainroad,
            @RequestParam(required = false) Boolean guestroom,
            @RequestParam(required = false) Boolean basement,
            @RequestParam(required = false) Boolean hotwaterheating,
            @RequestParam(required = false) Boolean airconditioning,
            @RequestParam(required = false) Boolean prefarea
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Status", "filterApartments executed");
        headers.add("version", "1.0 Api Rest Apartment Object");
        headers.add("active", "true");
        headers.add("author", "Albert");

        Specification<Apartment> spec = ApartmentSpecification.filterBy(
                maxPrice, minArea, minBedrooms, minBathrooms, minParking,
                furnishingStatus, mainroad, guestroom, basement,
                hotwaterheating, airconditioning, prefarea
        );

        List<Apartment> apartments = apartmentRepository.findAll(spec);
        long totalCount = apartmentRepository.count();
        long filteredCount = apartments.size();

        headers.add("total-apartments", String.valueOf(totalCount));
        headers.add("filtered-apartments", String.valueOf(filteredCount));
        headers.add("Status", "filterApartments success");

        return ResponseEntity.ok().headers(headers).body(apartments);
    }
}
