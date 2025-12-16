package com.example.apartment_predictor;

import com.example.apartment_predictor.model.Apartment;
import com.example.apartment_predictor.model.Review;
import com.example.apartment_predictor.repository.ApartmentRepository;
import com.example.apartment_predictor.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class ApartmentPredictorApplication implements CommandLineRunner {

    @Autowired
    private ApartmentRepository apartmentRepository;
    @Autowired
    private ReviewRepository reviewRepository;

	public static void main(String[] args) {
		SpringApplication.run(ApartmentPredictorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

        //testApartmentsInsert();
        //testReviewsInsert();

	}

    public void testApartmentsInsert() {
        System.out.println("Creating apartment objects and saving to db...");
        // Create apartment objects based on your sample data
        /*Apartment apartment1 = new Apartment(
                13300000L,    // price
                7420,         // area
                4,            // bedrooms
                2,            // bathrooms
                3,            // stories
                "yes",        // mainroad
                "no",         // guestroom
                "no",         // basement
                "no",         // hotwater
                "yes",        // airconditioning
                2,            // parking
                "yes",        // prefarea
                "furnished"   // furnishingstatus
        );*/

        // Create additional sample apartments
        /*Apartment apartment2 = new Apartment(
                8500000L,     // price
                5200,         // area
                3,            // bedrooms
                2,            // bathrooms
                2,            // stories
                "yes",        // mainroad
                "yes",        // guestroom
                "no",         // basement
                "yes",        // hotwater
                "yes",        // airconditioning
                1,            // parking
                "no",         // prefarea
                "semi-furnished" // furnishingstatus
        );

        Apartment apartment3 = new Apartment(
                6200000L,     // price
                3800,         // area
                2,            // bedrooms
                1,            // bathrooms
                1,            // stories
                "no",         // mainroad
                "no",         // guestroom
                "yes",        // basement
                "yes",        // hotwater
                "no",         // airconditioning
                0,            // parking
                "yes",        // prefarea
                "unfurnished" // furnishingstatus
        );

        // Display the created apartments
        //System.out.println("\n=== Created Apartments ===");
        //System.out.println("Apartment 1: " + apartment1);
        //System.out.println("\nApartment 2: " + apartment2);
        //System.out.println("\nApartment 3: " + apartment3);

        //System.out.println("\n=== Apartment Details ===");
        //printApartmentDetails(apartment1, "Luxury Apartment");
        //printApartmentDetails(apartment2, "Family Apartment");
        //printApartmentDetails(apartment3, "Budget Apartment");

        apartmentRepository.save(apartment1);
        apartmentRepository.save(apartment2);
        apartmentRepository.save(apartment3);

        int index = 0;
        System.out.println("\n=== Apartments in the Database ===");
        for (Apartment apartment : apartmentRepository.findAll()){
            index++;
            System.out.println("#" + index);
            System.out.println(apartment);
        }

        //apartmentRepository.findAll().forEach(System.out::println);*/
    }

    public void testReviewsInsert() {
        System.out.println("Creating review objects and saving to db ...");
        // Create review objects based on sample data
        Review review1 = new Review();
        //review1.setId(java.util.UUID.randomUUID().toString());
        review1.setTitle("Amazing apartment with great location");
        review1.setContent("This apartment exceeded my expectations. The location is perfect and the amenities are top-notch. Highly recommended for anyone looking for a comfortable stay.");
        review1.setRating(5);
        review1.setReviewDate(LocalDate.of(2024, 1, 15));

        Review review2 = new Review();
        //review2.setId(java.util.UUID.randomUUID().toString());
        review2.setTitle("Good value for money");
        review2.setContent("Nice apartment overall. Clean and well-maintained. The only downside is the parking situation but everything else was great.");
        review2.setRating(4);
        review2.setReviewDate(LocalDate.of(2024, 2, 3));

        Review review3 = new Review();
        //review3.setId(java.util.UUID.randomUUID().toString());
        review3.setTitle("Disappointing experience");
        review3.setContent("The apartment looked much better in photos. Several issues with plumbing and the heating system didn't work properly during our stay.");
        review3.setRating(2);
        review3.setReviewDate(LocalDate.of(2024, 2, 18));

        Review review4 = new Review();
        //review4.setId(java.util.UUID.randomUUID().toString());
        review4.setTitle("Perfect for families");
        review4.setContent("Spacious apartment with excellent facilities for children. The playground nearby and safe neighborhood make it ideal for families with kids.");
        review4.setRating(5);
        review4.setReviewDate(LocalDate.of(2024, 3, 5));

        Review review5 = new Review();
        //review5.setId(java.util.UUID.randomUUID().toString());
        review5.setTitle("Average stay");
        review5.setContent("Nothing special but nothing terrible either. Basic amenities and decent location. Would consider staying again if the price is right.");
        review5.setRating(3);
        review5.setReviewDate(LocalDate.of(2024, 3, 22));

        // Save reviews to database
        reviewRepository.save(review1);
        reviewRepository.save(review2);
        reviewRepository.save(review3);
        reviewRepository.save(review4);
        reviewRepository.save(review5);

        // Display all reviews in the database
        int index = 0;
        System.out.println("\n=== Reviews in the Database ===");
        for (Review review : reviewRepository.findAll()) {
            index++;
            System.out.println("#" + index);
            System.out.println(review);
        }
    }

   /* private void printApartmentDetails(Apartment apartment, String description) {
        System.out.println("\n" + description + ":");
        System.out.println("  Price: $" + apartment.getPrice());
        System.out.println("  Area: " + apartment.getArea() + " sq ft");
        System.out.println("  Bedrooms: " + apartment.getBedrooms());
        System.out.println("  Bathrooms: " + apartment.getBathrooms());
        System.out.println("  Stories: " + apartment.getStories());
        System.out.println("  Main Road Access: " + apartment.getMainroad());
        System.out.println("  Guest Room: " + apartment.getGuestroom());
        System.out.println("  Basement: " + apartment.getBasement());
        System.out.println("  Hot Water Heating: " + apartment.getHotwaterheating());
        System.out.println("  Air Conditioning: " + apartment.getAirconditioning());
        System.out.println("  Parking Spaces: " + apartment.getParking());
        System.out.println("  Preferred Area: " + apartment.getPrefarea());
        System.out.println("  Furnishing Status: " + apartment.getFurnishingstatus());
    }*/

}


