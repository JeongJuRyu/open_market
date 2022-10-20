package com.tmax.cm.superstore.order.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tmax.cm.superstore.order.entity.ShippingOrderSelectedOption;

public interface ShippingOrderSelectedOptionRepository extends JpaRepository<ShippingOrderSelectedOption, UUID> {
	@Query("select soso from ShippingOrderSelectedOption soso "
		+ "join fetch OrderOptionGroup oog "
		+ "join fetch OrderOption oo "
		+ "where soso.id = :id")
	Optional<ShippingOrderSelectedOption> findForReview(UUID id);
}
