package com.tmax.cm.superstore.user.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tmax.cm.superstore.user.dto.UpdateDeliveryRequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User implements UserDetails {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "USER_ID")
	private UUID id;

	@Column(unique = true, nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(unique = true, nullable = false)
	private String phoneNum;

	@Column(nullable = false)
	private String address;

	@OneToMany(mappedBy = "user")
	private List<DeliveryAddress> deliveryAddresses = new ArrayList<>();

	@ManyToMany
	@JoinTable(
		name = "user_authority",
		joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")},
		inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_NAME", referencedColumnName = "AUTHORITY_NAME")}
	)
	private Set<Authority> authorities;

	public void updateEmail(String email){
		this.email = email;
	}

	public void updatePassword(String newPassword){
		this.password = newPassword;
	}

	public void updateDeliveryAddress(UpdateDeliveryRequestDto updateDeliveryRequestDto){
		this.getDeliveryAddresses().clear();
		List<UpdateDeliveryRequestDto.DeliveryAddress> deliveryAddresses = updateDeliveryRequestDto
			.getDeliveryAddresses();
		for(UpdateDeliveryRequestDto.DeliveryAddress newDeliveryAddress : deliveryAddresses){
			DeliveryAddress newAddress = DeliveryAddress.builder()
				.address(newDeliveryAddress.getAddress())
				.user(this)
				.name(newDeliveryAddress.getName())
				.phoneNum(newDeliveryAddress.getPhoneNum())
				.build();
			this.getDeliveryAddresses().add(newAddress);
		}
	}
	@Column()
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
