package com.tmax.cm.superstore.item.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.item.entity.Option;

public interface OptionRepository extends JpaRepository<Option, UUID> {

}
