package com.tmax.cm.superstore.user.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.user.entities.UserLoginInfo;

public interface UserLoginInfoRepository extends JpaRepository<UserLoginInfo, String> {
}
