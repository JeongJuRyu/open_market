package com.tmax.cm.superstore.mypage.service;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tmax.cm.superstore.mypage.dto.GetAllCustomerInquiryForSellerRequestDto;
import com.tmax.cm.superstore.mypage.dto.GetAllCustomerInquiryForSellerResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetCustomerInquiryForSellerResponseDto;
import com.tmax.cm.superstore.mypage.dto.PostCustomerInquiryRequestDto;
import com.tmax.cm.superstore.mypage.dto.GetAlICustomerInquiryResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetCustomerInquiryResponseDto;
import com.tmax.cm.superstore.mypage.dto.UpdateCustomerInquiryRequestDto;
import com.tmax.cm.superstore.mypage.entity.CustomerInquiry;
import com.tmax.cm.superstore.mypage.error.exception.CustomerInquiryNotFoundException;
import com.tmax.cm.superstore.mypage.mapper.CustomerInquiryMapper;
import com.tmax.cm.superstore.mypage.repository.CustomerInquiryRepository;
import com.tmax.cm.superstore.seller.entity.Seller;
import com.tmax.cm.superstore.user.entities.User;
import com.tmax.cm.superstore.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerInquiryService {
	private final CustomerInquiryRepository customerInquiryRepository;
	private final UserRepository userRepository;
	private final CustomerInquiryMapper customerInquiryMapper;

	@Transactional(readOnly = true)
	public GetAlICustomerInquiryResponseDto getAllInquiry(){
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<CustomerInquiry> inquiryList = customerInquiryRepository.findAllByUserId(user.getId());
		return GetAlICustomerInquiryResponseDto.builder().build();
	}

	@Transactional(readOnly = true)
	public GetCustomerInquiryResponseDto getCustomerInquiry(UUID id){
		CustomerInquiry customerInquiry = customerInquiryRepository.findById(id)
			.orElseThrow(CustomerInquiryNotFoundException::new);
		return GetCustomerInquiryResponseDto.builder().build();
	}

	@Transactional
	public UUID postCustomerInquiry(PostCustomerInquiryRequestDto dto){
		CustomerInquiry customerInquiry = CustomerInquiry.builder().build();
		return customerInquiryRepository.save(customerInquiry).getId();
	}

	@Transactional
	public UUID updateCustomerInquiry(UpdateCustomerInquiryRequestDto dto){
		CustomerInquiry customerInquiry = customerInquiryRepository.findById(dto.getId())
			.orElseThrow(CustomerInquiryNotFoundException::new);
		customerInquiry.updateInquiry(dto);
		return dto.getId();
	}
	@Transactional
	public UUID deleteCustomerInquiry(UUID id){
		customerInquiryRepository.deleteById(id);
		return id;
	}

	@Transactional(readOnly = true)
	public GetAllCustomerInquiryForSellerResponseDto getAllInquiryForSeller(
		GetAllCustomerInquiryForSellerRequestDto dto){
		Seller seller = (Seller)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<CustomerInquiry> customerInquiries = customerInquiryRepository.findBySeller(seller.getSellerId())
			.stream().filter(inquiry -> inquiry.getIsProcessed() == dto.getIsProcessed()).toList();
		return GetAllCustomerInquiryForSellerResponseDto.builder()
			.customerInquiries(customerInquiryMapper.toCustomerInquiries(customerInquiries))
			.build();
	}

	@Transactional(readOnly = true)
	public GetCustomerInquiryForSellerResponseDto getInquiryForSeller(UUID customerInquiryId){
		CustomerInquiry customerInquiry = customerInquiryRepository.findByIdForSeller(customerInquiryId)
			.orElseThrow(CustomerInquiryNotFoundException::new);
		return customerInquiryMapper.toCustomerInquiryForSeller(customerInquiry);
	}

}
