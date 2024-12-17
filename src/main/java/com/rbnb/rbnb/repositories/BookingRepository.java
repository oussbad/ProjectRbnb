package com.rbnb.rbnb.repositories;



import com.rbnb.rbnb.model.Booking;
import com.rbnb.rbnb.model.Property;
import com.rbnb.rbnb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByClient(User client);

    List<Booking> findByProperty(Property property);

    List<Booking> findByStatus(String status);
}
