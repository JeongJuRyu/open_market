package com.tmax.cm.superstore.order.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tmax.cm.superstore.order.entity.PickupOrderSelectedOption;

public interface PickupOrderSelectedOptionRepository extends JpaRepository<PickupOrderSelectedOption, UUID> {
	@Query("select poso from PickupOrderSelectedOption poso "
		+ "join fetch OrderOptionGroup oog "
		+ "join fetch OrderOption oo "
		+ "where poso.id = :id")
	Optional<PickupOrderSelectedOption> findForReview(UUID id);
}
