# Derived Queries Lab

> These exercises reinforce **countBy**, **existsBy**, **OrderBy** (with multiple fields), **Top/First**, and realistic UX feedback — perfect for building toward the apartment search feature. Good luck!

## Exercise 1 – Basic Count

Implement a method in `ApartmentRepository` that returns the **number of apartments** that have air conditioning and at least 2 parking spots.  
Use a derived query method with `countBy...`.  
Call it from a service/controller and display: "X apartments match your criteria (with AC and ≥2 parking)".  
Bonus: Also add a count of apartments without balcony.

## Exercise 2 – Exists Check for Quick Validation

Create a derived query method that checks **if there exists** at least one apartment in a given price range (minPrice ≤ price ≤ maxPrice) that has a balcony.  

In the controller: if the method returns `false`, show a friendly message: "No apartments with balcony in your budget range — try increasing max price or removing balcony filter".

## Exercise 3 – Ordered List + Top Results

Write a derived query method that finds the **top 10 apartments** ordered by **averageRating descending**, but only those with balcony = true and reviewCount ≥ 5.  
Use `OrderByAverageRatingDesc` + `Top10` + conditions.  
Display them in the UI as "Highly rated apartments with balcony (sorted best first)".

## Exercise 4 – Combined Count + Ordered Results

Implement two methods:  
a) `long countByBedroomsGreaterThanEqualAndBalconyTrueAndPriceLessThanEqual(int minBedrooms, BigDecimal maxPrice);`  
   → Used to show "Found 28 family-sized apartments with balcony under your budget"  
b) A method that returns the cheapest 8 apartments** (ordered by price ascending) that have balcony = true, at least 3 bedrooms, and airconditioning = true.  
   → `List<Apartment> findTop8ByBalconyTrueAndBedroomsGreaterThanEqualAndAirconditioningTrueOrderByPriceAsc(...)`

## Exercise 5 – Realistic Multi-criteria Search with Exists & Ordered Fallback

Create a search endpoint that accepts optional parameters: minPrice, maxPrice, minBedrooms, hasBalcony (Boolean), minRating.  

- First, use an `existsBy...` method to quickly check if anything matches the strict filters (price range + balcony + minRating).  
- If yes → return the top 12 results ordered by averageRating descending, then by price ascending (use `OrderByAverageRatingDescPriceAsc`).  
- If no → fallback to a count method showing how many would match **without** the balcony or rating constraint, and suggest relaxing filters.  
  Try to keep as many methods as possible derived (no `@Query` yet).
