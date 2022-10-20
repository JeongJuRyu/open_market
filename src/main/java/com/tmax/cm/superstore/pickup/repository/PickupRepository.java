package com.tmax.cm.superstore.pickup.repository;

import com.tmax.cm.superstore.pickup.entity.Pickup;
import com.tmax.cm.superstore.shipping.entity.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.util.UUID;

public interface PickupRepository extends JpaRepository<Pickup, UUID> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select pu from Pickup pu where pu.id = :id")
    Pickup findWithIdForUpdate(@Param("id") UUID id);
}
