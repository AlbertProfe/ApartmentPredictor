# 2026 Spring Query Report



> In **2026**, among professional big players (large enterprises, banks, insurance companies, e-commerce giants, telecoms, government systems, etc. using **Spring Boot + JPA/Hibernate** at scale), the dominant pattern is a **hybrid approach** — but with very clear preferences based on query type.

### Most common real-world usage split in large-scale Java backends (2025–2026 trends)

- **~70–85% of queries**: **JPQL** (or derived query methods / `@Query` annotations)  
  This is the **default and most widely used** in production at scale.  
  Reasons:  
  
  - Extremely readable (SQL-like but on entities)  
  - Concise for static / mostly-fixed queries (reports, CRUD, standard business logic)  
  - Portable across databases  
  - Easy to review, debug, and tune (you see the exact query pattern)  
  - Spring Data JPA derived methods (`findByLastNameAndStatus(...)`) compile to JPQL under the hood → even more usage indirectly  
  - Many legacy/large monoliths migrated from older codebases already use JPQL heavily

- **~10–25% of queries**: **Dynamic / complex filtering** → **JPA Specifications** (which is **Criteria API** under the hood)  
  This is the go-to when you need:  
  
  - Optional filters (advanced search forms, reporting dashboards with 5–15 toggles)  
  - Runtime-composed predicates  
  - Reusable query fragments across services  
    Large companies almost never use **raw Criteria API** (CriteriaBuilder + Root + Predicate manually) anymore — it's considered too verbose and boilerplate-heavy.  
    Instead, they wrap it in **Spring Data JPA Specifications** (very common pattern since ~2015–2016, still strongly recommended in 2026 best practices).

- **QueryDSL**: Still used in some shops (especially those that adopted it early ~2010–2018), but **adoption has plateaued or declined** relative to Specifications.  
  
  - Pros: much nicer fluent syntax than pure Criteria  
  - Cons: extra annotation processor + build step + dependency + generated Q-classes bloat  
    → Many big players prefer sticking to pure Spring/JPA ecosystem (Specifications) to reduce build complexity and vendor lock-in risk.

- **Native SQL**: Only for the remaining **~5–10%** (very complex analytics, DB-specific features like window functions/CTEs/full-text, extreme performance tuning, or when JPQL/Specifications generate suboptimal SQL).

### Quick 2026 "big player" reality check

| Company/Scale Type          | Typical Mix (2026)                          | Notes / Why                                                      |
| --------------------------- | ------------------------------------------- | ---------------------------------------------------------------- |
| Banks / Finance (large)     | 80% JPQL + derived, 15–20% Specifications   | Stability, auditability, portability matter more than fancy DSLs |
| E-commerce / Retail giants  | 70% JPQL, 25% Specifications, some QueryDSL | Heavy dynamic search/filtering → Specifications shine            |
| Insurance / Telco monoliths | 85–90% JPQL / @Query                        | Lots of static reporting + legacy code                           |
| Modern microservices teams  | 60–70% JPQL, 25–35% Specifications          | Dynamic APIs common; avoid extra deps like QueryDSL              |
| Startups / scale-ups        | More QueryDSL or even jOOQ in some cases    | But big players rarely switch away from JPA-native               |

### Bottom line in 2026

- **JPQL** (including derived queries and `@Query`) remains the **clear #1 choice** for the majority of queries in serious enterprise Java applications — it's simple, fast to write, easy to maintain, and "good enough" for most needs.
- **Criteria API** is **not** the everyday tool — it's almost always hidden behind **Spring Data JPA Specifications** when dynamic queries are required.
- Pure/hand-written Criteria API code (without Specifications) is rare in new 2025–2026 code at big companies — it's seen as outdated/verbose.
- No one forces "always Criteria" anymore (that was a 2010–2015 hype); the industry settled on **"JPQL first, Specifications when dynamic"**.


