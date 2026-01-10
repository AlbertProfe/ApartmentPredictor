package com.example.apartment_predictor.lab01;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PropertyTypePolymorphismTest {

    @Test
    void getPropertyType_returnsCorrectType_forEachSubclass() {
        Property apartment = new Apartment(90.0, 3, 2);
        Property house = new House(80.0, 3, 3, true);
        Property duplex = new Duplex(250.0, 3, 6, false);

        assertEquals("Apartment", apartment.getPropertyType());
        assertEquals("Single-Family House", house.getPropertyType());
        assertEquals("Duplex (2 Units)", duplex.getPropertyType());
    }

    @Test
    void getPropertyType_polymorphismWorks_insideAListOfProperty() {
        List<Property> properties = List.of(
                new Apartment(90.0, 3, 2),
                new House(80.0, 3, 3, true),
                new Duplex(250.0, 3, 6, false)
        );

        List<String> types = properties.stream()
                .map(Property::getPropertyType)
                .toList();

        assertEquals(List.of("Apartment", "Single-Family House", "Duplex (2 Units)"), types);
    }
}
