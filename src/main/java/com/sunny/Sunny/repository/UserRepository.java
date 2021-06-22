package com.sunny.Sunny.repository;

import com.sunny.Sunny.constants.AuthConstants;
import com.sunny.Sunny.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users " +
            "SET auth = "+ AuthConstants.ACTIVATED +" WHERE email = ?1", nativeQuery = true)
    int activeUser(String email);

    Boolean existsByEmail(String email);
}
