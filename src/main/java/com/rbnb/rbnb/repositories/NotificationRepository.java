package com.rbnb.rbnb.repositories;


import com.rbnb.rbnb.model.Notification;
import com.rbnb.rbnb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserAndRead(User user, boolean read);

    List<Notification> findByUser(User user);
    List<Notification> findByUserIdOrderByTimestampDesc(Long userId); // Use "user.id" to reference the user ID

}
