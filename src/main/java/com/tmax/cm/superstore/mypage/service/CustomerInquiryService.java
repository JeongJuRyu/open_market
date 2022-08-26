package com.tmax.cm.superstore.mypage.service;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tmax.cm.superstore.mypage.dto.CreateCustomerInquiryRequestDto;
import com.tmax.cm.superstore.mypage.dto.GetAlICustomerCenterInquiryResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetCustomerInquiryResponseDto;
import com.tmax.cm.superstore.mypage.dto.UpdateCustomerInquiryRequestDto;
import com.tmax.cm.superstore.mypage.entity.CustomerInquiry;
import com.tmax.cm.superstore.mypage.error.exception.CustomerInquiryNotFoundException;
import com.tmax.cm.superstore.mypage.repository.CustomerInquiryRepository;
import com.tmax.cm.superstore.user.entities.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerInquiryService {
	private final CustomerInquiryRepository customerInquiryRepository;

	@Transactional(readOnly = true)
	public GetAlICustomerCenterInquiryResponseDto getAllInquiry(){
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<CustomerInquiry> inquiryList = customerInquiryRepository.findAllInquiryByUserId(user.getId());
		return GetAlICustomerCenterInquiryResponseDto.builder().build();
	}

	@Transactional(readOnly = true)
	public GetCustomerInquiryResponseDto getCustomerInquiry(UUID id){
		CustomerInquiry customerInquiry = customerInquiryRepository.findById(id)
			.orElseThrow(CustomerInquiryNotFoundException::new);
		return GetCustomerInquiryResponseDto.builder().build();
	}

	@Transactional
	public UUID postCustomerInquiry(CreateCustomerInquiryRequestDto dto){
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
}
