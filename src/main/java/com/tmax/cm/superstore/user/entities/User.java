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
@Table(name="users")
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

	// @Column(nullable = false)
	private String userName;

	// @Column(nullable = false)
	private String nickName;

	// @OneToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "EMAIL_TOKEN_ID")
	// private EmailToken emailToken;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@Builder.Default
	private List<DeliveryAddress> deliveryAddresses = new ArrayList<>();

	@ManyToMany
	@JoinTable(
		name = "user_authority",
		joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")},
		inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_NAME", referencedColumnName = "AUTHORITY_NAME")}
	)
	private Set<Authority> authorities;

//	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//	@Builder.Default
//	private List<WishlistGroup> wishlistGroups = new ArrayList<>();


	public void updateEmail(String email){
		this.email = email;
	}

	public void updatePassword(String newPassword){
		this.password = newPassword;
	}

	public void postDeliveryAddress(PostDeliveryRequestDto dto){
		Boolean isFirstAddress = this.deliveryAddresses.size() == 0 ? true : dto.isDefaultAddress();
		DeliveryAddress deliveryAddress = DeliveryAddress.builder()
			.recipient(dto.getRecipient())
			.user(this)
			.mobile(dto.getMobile())
			.isDefaultAddress(isFirstAddress).build();
		this.getDeliveryAddresses().add(deliveryAddress);
	}

	public void updateDeliveryAddress(UpdateDeliveryInfoRequestDto dto){
		DeliveryAddress deliveryAddress = this.getDeliveryAddresses()
			.stream().filter(address -> address.getId() == dto.getShippingAddressId())
			.findAny().orElseThrow(DeliveryAddressNotFoundException::new);
		DeliveryAddress newDeliveryAddress = DeliveryAddress.builder()
			.recipient(dto.getRecipient())
			.user(this)
			.mobile(dto.getMobile())
			.isDefaultAddress(dto.isDefaultAddress()).build();
		this.getDeliveryAddresses().remove(deliveryAddress);
		this.getDeliveryAddresses().add(newDeliveryAddress);
	}

	public void setDeliveryAddress(UUID id){
		DeliveryAddress newDeliveryAddress = this.getDeliveryAddresses()
			.stream().filter(address -> address.getId() == id)
			.findAny().orElseThrow(DeliveryAddressNotFoundException::new);
		DeliveryAddress oldDeliveryAddress = this.getDeliveryAddresses()
			.stream().filter(address -> address.getIsDefaultAddress())
			.findAny().orElseThrow(DeliveryAddressNotFoundException::new);
		newDeliveryAddress.setDefaultAddress(oldDeliveryAddress);
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
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

}
