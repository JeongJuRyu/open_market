package com.tmax.cm.superstore.user.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.user.entities.User;

public interface UserRepository extends JpaRepository<User, UUID> {
	Optional<User> findUserByEmail(String email);
}
