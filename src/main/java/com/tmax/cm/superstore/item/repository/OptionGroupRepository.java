package com.tmax.cm.superstore.item.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmax.cm.superstore.item.entity.OptionGroup;

public interface OptionGroupRepository extends JpaRepository<OptionGroup, UUID> {
    
}
