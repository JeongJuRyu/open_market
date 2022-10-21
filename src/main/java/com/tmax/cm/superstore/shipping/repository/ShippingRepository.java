package com.tmax.cm.superstore.shipping.repository;

import java.util.UUID;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import com.tmax.cm.superstore.shipping.entity.Shipping;

public interface ShippingRepository extends JpaRepository<Shipping, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select sh from Shipping sh where sh.id = :id")
    Shipping findWithIdForUpdate(UUID id);
}
