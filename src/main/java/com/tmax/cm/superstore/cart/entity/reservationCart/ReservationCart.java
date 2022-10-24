package com.tmax.cm.superstore.cart.entity.reservationCart;

import com.tmax.cm.superstore.code.CartType;
import com.tmax.cm.superstore.user.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Getter
@Table(name = "reservationCart")
@Access(AccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "ReservationCartBuilder")
public class ReservationCart {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;

	@NotNull
	@OneToOne(fetch = FetchType.LAZY)
	private User user;

	private CartType cartType;

	public static ReservationCartBuilder builder(CartType cartType, User user) {
		return ReservationCartBuilder()
			.cartType(cartType)
			.user(user);
	}
}
