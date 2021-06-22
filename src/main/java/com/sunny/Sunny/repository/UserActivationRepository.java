package com.sunny.Sunny.repository;

import com.sunny.Sunny.entity.User;
import com.sunny.Sunny.entity.UserActivation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UserActivationRepository extends JpaRepository<UserActivation, Integer> {

    Optional<UserActivation> getByToken(String token);

    @Transactional
    @Modifying
    @Query(value = "UPDATE user_activation " +
            "SET confirmed_at = ?2 " +
            "WHERE token = ?1", nativeQuery = true)
    int setConfirmAt(String token, LocalDateTime confirmedAt);
}
