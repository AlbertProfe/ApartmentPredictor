package com.example.apartment_predictor.controller;

import com.example.apartment_predictor.model.Apartment;
import com.example.apartment_predictor.service.ApartmentService;
import com.example.apartment_predictor.utils.PopulateDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("aptPredictor/v1/apartments")
public class ApartmentRestController {

    @Autowired
    ApartmentService apartmentService;

    @Autowired
    PopulateDB populateDB;

    // default mapping at aptPredictor/v1/apartments
    @GetMapping("")
    public ResponseEntity<Iterable<Apartment>> getAllApartments(){
        return ResponseEntity.ok().body(apartmentService.findAll());
        //return apartmentService.findAll();
    }

    @GetMapping("/{id}")
    public Apartment getApartmentById(@PathVariable String id){
        return apartmentService.findApartmentById(id);
    }

    @PostMapping("/create")
    public Apartment createApartment(@RequestBody Apartment apartment){
        //apartment.setId(UUID.randomUUID().toString());
        return apartmentService.createApartment(apartment);
    }

    @PutMapping("/update/{id}")
    public Apartment updateApartment(@PathVariable String id, @RequestBody Apartment apartment){
        return apartmentService.updateApartmentById(id, apartment);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteApartmentById(@PathVariable String id){
        apartmentService.deleteApartment(id);
    }

    @GetMapping("/populate")
    public ResponseEntity<String> populateApartments(@RequestParam int qty) {
        int qtyApartmentsCreated = populateDB.populateApartments(qty);
        if (qtyApartmentsCreated > 0)
            return ResponseEntity.ok("Populated apartments: " + qtyApartmentsCreated);
        else
            return ResponseEntity.badRequest().body("Failed to populate apartments");
    }
}
