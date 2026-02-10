# **JPA Relationships Implementation Rule**

> This rule will help you systematically implement any JPA relationship by following these steps in order. 
> 
> The memory-rule includes the complete pattern example using your Apartment-Review relationship as a reference.

**Cardinality > Annotation > Config > Join > Extra-config**

## The Real-World Scenario

Think about it like this:

- 🏠 **One Apartment** (e.g., "Luxury Apartment on 5th Avenue")
- 📝 **Multiple Reviews** for that apartment:
  - "Great location and amenities!" - ⭐⭐⭐⭐⭐
  - "Clean and comfortable" - ⭐⭐⭐⭐
  - "Perfect for business trips" - ⭐⭐⭐⭐⭐

```java
// ✅ GOOD: Proper relationship mapping
@Entity
public class Apartment {
    @Id
    private String id;
    private String address;

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();
}

@Entity
public class Review {
    @Id
    private String id;
    private String content;
    private int rating;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;
}
```

![](https://raw.githubusercontent.com/AlbertProfe/ApartmentPredictor/refs/heads/master/docs/diagrams/CACAJEC.png)

## **0. Cardinality Between Entities**

- Analyze the business relationship (One apartment → Many reviews)
- Choose appropriate annotation type

## **1. Write the Annotation**

- `@OneToMany` on the "one" side
- `@ManyToOne` on the "many" side

## **2. Configure the Annotation**

- `mappedBy` for bidirectional relationships
- `cascade` for operation propagation
- `fetch` for performance optimization

## **3. Write the Join**

- `@JoinColumn(name = "foreign_key")` on owning side
- `@JoinTable` for many-to-many relationships

## **4. Extra Configuration**

- Initialize collections with `new ArrayList<>()`
- Add helper methods for bidirectional relationships
- Handle both sides of the relationship in code
- `@JsonIgnore` and circular reference
