package com.tmax.cm.superstore.shipping.repository;

import com.tmax.cm.superstore.shipping.entity.Shipping;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.UUID;

public interface ShippingRepository extends JpaRepository<Shipping, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select sh from Shipping sh where sh.id = :id")
    Shipping findWithIdForUpdate(UUID id);
}
