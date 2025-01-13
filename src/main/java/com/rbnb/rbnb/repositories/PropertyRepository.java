package com.rbnb.rbnb.repositories;

import com.rbnb.rbnb.model.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    // Find properties by city
    List<Property> findByCity(String city);

    // Find properties by host
    List<Property> findByHostId(Long hostId);

    // Find properties by max price
    List<Property> findByPricePerNightLessThanEqual(double maxPrice);

    // Find properties by minimum number of bedrooms
    List<Property> findByBedroomsGreaterThanEqual(int bedrooms);

    // Combined search query for filtering properties by city, price, and bedrooms
    @Query("SELECT p FROM Property p WHERE " +
            "(:city IS NULL OR p.city = :city) AND " +
            "(:maxPrice IS NULL OR p.pricePerNight <= :maxPrice) AND " +
            "(:minBedrooms IS NULL OR p.bedrooms >= :minBedrooms)")
    List<Property> searchProperties(@Param("city") String city,
                                    @Param("maxPrice") Double maxPrice,
                                    @Param("minBedrooms") Integer minBedrooms);

    // Combined search query with pagination
    @Query("SELECT p FROM Property p WHERE " +
            "(:city IS NULL OR p.city = :city) AND " +
            "(:maxPrice IS NULL OR p.pricePerNight <= :maxPrice) AND " +
            "(:minBedrooms IS NULL OR p.bedrooms >= :minBedrooms)")
    Page<Property> searchPropertiesWithPagination(@Param("city") String city,
                                                  @Param("maxPrice") Double maxPrice,
                                                  @Param("minBedrooms") Integer minBedrooms,
                                                  Pageable pageable);
    @Query("SELECT p FROM Property p WHERE " +
            "( p.city = :city) AND " +
            "( p.pricePerNight <= :maxPrice) AND " +
            "( p.bedrooms >= :numberOfRooms) AND " +
            "NOT EXISTS (SELECT b FROM Booking b WHERE b.property = p AND " +
            "(b.startDate < :endDate AND b.endDate > :startDate))")
    List<Property> findAvailableProperties(
            @Param("city") String city,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("maxPrice") Double maxPrice,
            @Param("numberOfRooms") Integer numberOfRooms);

}
