package com.tmax.cm.superstore.user.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tmax.cm.superstore.user.entities.User;

public interface UserRepository extends JpaRepository<User, UUID> {
	Optional<User> findUserByEmail(String email);
	boolean existsByEmail(String email);
	boolean existsByPhoneNum(String phoneNum);
	@Query(value = "SELECT * FROM USERS AS U JOIN REVIEW AS R ON R.USER_ID = U.ID WHERE R.ID = :reviewId", nativeQuery = true)
	Optional<User> findByReviewId(UUID reviewId);
}
