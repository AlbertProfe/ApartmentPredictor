package com.example.apartment_predictor.service;

import com.example.apartment_predictor.model.Apartment;
import com.example.apartment_predictor.repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApartmentService {

    @Autowired
    ApartmentRepository apartmentRepository;

    public Iterable<Apartment> findAll() {
        return apartmentRepository.findAll();
    }

    public Apartment createApartment(Apartment apartment){
        return apartmentRepository.save(apartment);
    }

    public Apartment updateApartment (Apartment apartment){

       return apartmentRepository.save(apartment);
    }

    public Apartment updateApartmentById(String id, Apartment apartment) {
        if (id == null || apartment == null) {
            return null;
        }

        Optional<Apartment> existingOpt = apartmentRepository.findById(id);
        if (existingOpt.isEmpty()) {
            return null;
        }

        Apartment existing = existingOpt.get();

        if (apartment.getArea() != null) existing.setArea(apartment.getArea());
        if (apartment.getBedrooms() != null) existing.setBedrooms(apartment.getBedrooms());
        if (apartment.getBathrooms() != null) existing.setBathrooms(apartment.getBathrooms());
        if (apartment.getStories() != null) existing.setStories(apartment.getStories());
        if (apartment.isGuestroom()) existing.setGuestroom(apartment.isGuestroom());
        if (apartment.isBasement()) existing.setBasement(apartment.isBasement());
        if (apartment.isHotwaterheating()) existing.setHotwaterheating(apartment.isHotwaterheating());
        if (apartment.isAirconditioning()) existing.setAirconditioning(apartment.isAirconditioning());
        if (apartment.isParking()) existing.setParking(apartment.isParking());
        if (apartment.getFurnishingstatus() != null) existing.setFurnishingstatus(apartment.getRawFurnishingstatus());

        return apartmentRepository.save(existing);
    }

    public void deleteApartment (String id){
        apartmentRepository.deleteById(id);
    }

    public Apartment findApartmentById (String id){
        Optional<Apartment> found = apartmentRepository.findById(id);
        return found.orElse(null);
    }




}
