package com.example.apartment_predictor;

import com.example.apartment_predictor.model.*;
import com.example.apartment_predictor.repository.ApartmentRepository;
import com.example.apartment_predictor.repository.ReviewRepository;
import com.example.apartment_predictor.utils.PrintingUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@SpringBootTest
class ApartmentPredictorApplicationTests {

    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    ApartmentRepository apartmentRepository;
    @Autowired
    PrintingUtils printingUtils;

    @Test
    void testApartmentsInsert() {

        Apartment apartment1 = new Apartment();
        apartment1.setArea(5);
        //apartment1.setAirconditioning("yes");
        // ....
        apartmentRepository.save(apartment1);
        System.out.println("Apartment saved: " + apartment1);

       /* // Display all apartments in the database
        int index = 0;
        System.out.println("\n=== Apartments in the Database ===");
        for (Apartment apartment : apartmentRepository.findAll()) {
            index++;
            System.out.println("#" + index);
            System.out.println(apartment);
        }*/

        // Test all PrintingUtils methods
        // Generic List
        PrintingUtils.printList(List.copyOf((Collection<Apartment>) apartmentRepository.findAll()));
        PrintingUtils.printList(List.copyOf((Collection<Apartment>) apartmentRepository.findAll()), "Using printList with title");
        // Sending data by parameter, using CrudRepository
        PrintingUtils.printApartments(apartmentRepository);
        PrintingUtils.printApartmentsList(apartmentRepository.findAll());
        // Sending data by parameter, using Iterable and generic
        PrintingUtils.printObjectsList(apartmentRepository.findAll());
        // Calling it as an instance method, using Iterable and generic
        printingUtils.printApartmentsByRepoInstance();

        //PrintingUtils.printApartmentsByRepo();
        //PrintingUtils utils = new PrintingUtils();
        // Trying to call it as an instance method
        //utils.printApartmentsByRepoInstance();

        Review review1 = new Review();
        review1.setContent("This apartment exceeded my expectations. The location is perfect and the amenities are top-notch. Highly recommended for anyone looking for a comfortable stay.");
        review1.setRating(5);
        review1.setTitle("Nice Apartment in Fifth Avenue");
        review1.setReviewDate(LocalDate.now());
        review1.setApartment(apartment1);

        reviewRepository.save(review1);

        //apartment1.getReviews().add(review1);

        apartmentRepository.save(apartment1);

        /*// Display all apartments in the database
        index = 0;
        System.out.println("\n=== Apartments in the Database ===");
        for (Apartment apartment : apartmentRepository.findAll()) {
            index++;
            System.out.println("#" + index);
            System.out.println(apartment);
        }*/
    }

    @Test
    void testReviewsInsert() {


        Review review1 = new Review();
        // after constructor is called
        // id is generated automatically bY UUID
        review1.setContent("This apartment exceeded my expectations. The location is perfect and the amenities are top-notch. Highly recommended for anyone looking for a comfortable stay.\t");
        review1.setRating(5);
        review1.setTitle("Nice Apartment in Fifth Avenue");
        review1.setReviewDate(LocalDate.now());
        // save review object to H2 test local-database
        // JPA repository SAVES it USING the method SAVE from crudRepository
        // reviewRepository uses autowired dependency injection
        // ReviewRepository is an interface AND extends CrudRepository
        reviewRepository.save(review1);


        System.out.println("Review saved: " + review1);

        // Display all reviews in the database
        int index = 0;
        System.out.println("\n=== Reviews in the Database ===");
        for (Review review : reviewRepository.findAll()) {
            index++;
            System.out.println("#" + index);
            System.out.println(review);
        }


    }

    @Test
    void testHOAcalculation() {
        ArrayList<ResidentialProperty> properties = new ArrayList<>();

        properties.add(new Apartment(40, "hola", 3, 2, 2, true, true, 7));
        properties.add(new House(30, "hola", 2, 2, 2, true, 20, true, true, true, true, 20, true, 30));
        properties.add(new Duplex(70, "hola", 2, 9, 2, true, 20, true, true, false, false, 20, true));
        properties.add(new Townhouse(70, "hola", 2, 9, 2, true, 20, true, true, false, false, 20, true));

        for (ResidentialProperty property : properties) {
            if (property.requiresHomeownersAssociation()) {
                System.out.println(property + " requiere una asociación de propietarios");
            }
            else {
                System.out.println(property + " no requiere una asociación de propietarios");
            }
        }
    }

}
