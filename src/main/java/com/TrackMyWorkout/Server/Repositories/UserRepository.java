package com.TrackMyWorkout.Server.Repositories;

import com.TrackMyWorkout.Server.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Auto Query generate by JPA
    User findByUserName(String userName);
}
