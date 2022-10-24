package com.tmax.cm.superstore.cancel.repository;

import com.tmax.cm.superstore.cancel.entity.Cancel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.util.UUID;

public interface CancelRepository extends JpaRepository<Cancel, UUID> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select c from Cancel c where c.id = :id")
    Cancel findWithIdForUpdate(@Param("id") UUID id);
}
