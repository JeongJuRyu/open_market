package com.tmax.cm.superstore.user.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tmax.cm.superstore.user.dto.PostDeliveryRequestDto;
import com.tmax.cm.superstore.user.dto.UpdateDeliveryInfoRequestDto;
import com.tmax.cm.superstore.user.error.exception.DeliveryAddressNotFoundException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "users")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE users SET is_deleted = true WHERE user_id = ?")
public class User implements UserDetails {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "USER_ID", columnDefinition = "BINARY(16)")
	private UUID id;

	@Column(unique = true, nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(unique = true, nullable = false)
	private String phoneNum;

	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	@Builder.Default
	private Boolean isDeleted = false;

	@Column(nullable = false)
	@Builder.Default
	// false면 계정 만료
	private Boolean accountNonExpired = true;

	@Column(nullable = false)
	@Builder.Default
	// false면 계정 잠김
	private Boolean accountNonLocked = true;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@Builder.Default
	private List<DeliveryAddress> deliveryAddresses = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "user_authority", joinColumns = {
			@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID") }, inverseJoinColumns = {
					@JoinColumn(name = "AUTHORITY_NAME", referencedColumnName = "AUTHORITY_NAME") })
	private Set<Authority> authorities;

	// @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval =
	// true)
	// @Builder.Default
	// private List<WishlistGroup> wishlistGroups = new ArrayList<>();

	public void updateEmail(String email) {
		this.email = email;
	}

	public void updatePassword(String newPassword) {
		this.password = newPassword;
	}

	public DeliveryAddress postDeliveryAddress(PostDeliveryRequestDto dto) {
		Boolean isFirstAddress = this.deliveryAddresses.size() == 0 ? true : dto.isDefaultAddress();
		DeliveryAddress deliveryAddress = DeliveryAddress.builder()
				.recipient(dto.getRecipient())
				.user(this)
				.address(dto.getAddress())
			    .recipient(dto.getRecipient())
				.mobile(dto.getMobile())
				.requests(dto.getRequests())
				.isDefaultAddress(isFirstAddress).build();
		this.getDeliveryAddresses().add(deliveryAddress);
		return deliveryAddress;
	}

	public void updateDeliveryAddress(UpdateDeliveryInfoRequestDto dto) {
		DeliveryAddress deliveryAddress = this.getDeliveryAddresses()
				.stream().filter(address -> address.getId() == dto.getShippingAddressId())
				.findAny().orElseThrow(DeliveryAddressNotFoundException::new);
		DeliveryAddress newDeliveryAddress = DeliveryAddress.builder()
				.recipient(dto.getRecipient())
			    .address(dto.getAddress())
				.user(this)
				.mobile(dto.getMobile())
				.requests(dto.getRequests())
				.isDefaultAddress(dto.isDefaultAddress()).build();
		this.getDeliveryAddresses().remove(deliveryAddress);
		this.getDeliveryAddresses().add(newDeliveryAddress);
	}

	public void setDeliveryAddress(UUID id) {
		DeliveryAddress newDeliveryAddress = this.getDeliveryAddresses()
				.stream().filter(address -> address.getId() == id)
				.findAny().orElseThrow(DeliveryAddressNotFoundException::new);
		DeliveryAddress oldDeliveryAddress = this.getDeliveryAddresses()
				.stream().filter(DeliveryAddress::getIsDefaultAddress)
				.findAny().orElseThrow(DeliveryAddressNotFoundException::new);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
