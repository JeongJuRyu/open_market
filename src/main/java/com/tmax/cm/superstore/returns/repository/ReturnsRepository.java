package com.tmax.cm.superstore.returns.repository;

import com.tmax.cm.superstore.cancel.entity.Cancel;
import com.tmax.cm.superstore.returns.entity.Returns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.util.UUID;

public interface ReturnsRepository extends JpaRepository<Returns, UUID> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select r from Returns r where r.id = :id")
    Returns findWithIdForUpdate(@Param("id") UUID id);
}
