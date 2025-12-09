package com.example.apartment_predictor;

import com.example.apartment_predictor.model.Apartment;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApartmentPredictorApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ApartmentPredictorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Creating apartment objects...");
		
		// Create apartment objects based on your sample data
		Apartment apartment1 = new Apartment(
				13300000L,    // price
				7420,         // area
				4,            // bedrooms
				2,            // bathrooms
				3,            // stories
				"yes",        // mainroad
				"no",         // guestroom
				"no",         // basement
				"no",         // hotwater
				"yes",        // heating
				"yes",        // airconditioning
				2,            // parking
				"yes",        // prefarea
				"furnished"   // furnishingstatus
		);


		
		// Create additional sample apartments
		Apartment apartment2 = new Apartment(
				8500000L,     // price
				5200,         // area
				3,            // bedrooms
				2,            // bathrooms
				2,            // stories
				"yes",        // mainroad
				"yes",        // guestroom
				"no",         // basement
				"yes",        // hotwater
				"no",         // heating
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
				"no",         // heating
				"no",         // airconditioning
				0,            // parking
				"yes",        // prefarea
				"unfurnished" // furnishingstatus
		);
		
		// Display the created apartments
		System.out.println("\n=== Created Apartments ===");
		System.out.println("Apartment 1: " + apartment1);
		System.out.println("\nApartment 2: " + apartment2);
		System.out.println("\nApartment 3: " + apartment3);
		
		System.out.println("\n=== Apartment Details ===");
		printApartmentDetails(apartment1, "Luxury Apartment");
		printApartmentDetails(apartment2, "Family Apartment");
		printApartmentDetails(apartment3, "Budget Apartment");
	}
	
	private void printApartmentDetails(Apartment apartment, String description) {
		System.out.println("\n" + description + ":");
		System.out.println("  Price: $" + apartment.getPrice());
		System.out.println("  Area: " + apartment.getArea() + " sq ft");
		System.out.println("  Bedrooms: " + apartment.getBedrooms());
		System.out.println("  Bathrooms: " + apartment.getBathrooms());
		System.out.println("  Stories: " + apartment.getStories());
		System.out.println("  Main Road Access: " + apartment.getMainroad());
		System.out.println("  Guest Room: " + apartment.getGuestroom());
		System.out.println("  Basement: " + apartment.getBasement());
		System.out.println("  Hot Water: " + apartment.getHotwater());
		System.out.println("  Heating: " + apartment.getHeating());
		System.out.println("  Air Conditioning: " + apartment.getAirconditioning());
		System.out.println("  Parking Spaces: " + apartment.getParking());
		System.out.println("  Preferred Area: " + apartment.getPrefarea());
		System.out.println("  Furnishing Status: " + apartment.getFurnishingstatus());
	}

}
