// package com.tmax.cm.superstore.mypage.service;
//
// import java.util.List;
// import java.util.UUID;
//
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
//
// import com.tmax.cm.superstore.mypage.dto.GetAllItemInquiryResponseDto;
// import com.tmax.cm.superstore.mypage.dto.GetItemInquiryResponseDto;
// import com.tmax.cm.superstore.mypage.dto.PostItemInquiryRequestDto;
// import com.tmax.cm.superstore.mypage.entity.ItemInquiry;
// import com.tmax.cm.superstore.mypage.entity.ItemInquiryImage;
// import com.tmax.cm.superstore.mypage.error.exception.ItemInquiryNotFoundException;
// import com.tmax.cm.superstore.mypage.mapper.ItemInquiryMapper;
// import com.tmax.cm.superstore.mypage.repository.ItemInquiryRepository;
// import com.tmax.cm.superstore.user.entities.User;
//
// import lombok.RequiredArgsConstructor;
//
// @Service
// @RequiredArgsConstructor
// public class ItemInquiryService {
// 	private final ItemInquiryRepository itemInquiryRepository;
// 	private final ItemInquiryMapper itemInquiryMapper;
//
// 	@Transactional(readOnly = true)
// 	public GetAllItemInquiryResponseDto getAllItemInquiry(){
// 		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
// 		List<ItemInquiry> itemInquiries = itemInquiryRepository.findAllByUserId(user.getId());
// 		return GetAllItemInquiryResponseDto.builder()
// 			.itemInquiries(itemInquiryMapper.toItemInquiriesDto(itemInquiries)).build();
// 	}
//
// 	@Transactional(readOnly = true)
// 	public GetItemInquiryResponseDto getItemInquiry(UUID itemInquiryId){
// 		ItemInquiry itemInquiry= itemInquiryRepository.findByIdWithReply(itemInquiryId)
// 			.orElseThrow(ItemInquiryNotFoundException::new);
// 		return GetItemInquiryResponseDto.builder()
// 			.itemInquiry(itemInquiryMapper.toItemInquiryDto(itemInquiry))
// 			.build();
// 	}
//
// 	@Transactional
// 	public UUID postItemInquiry(
// 		PostItemInquiryRequestDto dto){
// 		ItemInquiry itemInquiry = ItemInquiry.builder()
// 			.title(dto.getTitle()).content(dto.getContent()).build();
// 		dto.getItemInquiryImages().forEach(image->{
// 			itemInquiry.getItemInquiryImages().add(ItemInquiryImage
// 				.itemsInquiryImageBuilder().url(image.getUrl()).build());
// 		});
// 		return itemInquiryRepository.save(itemInquiry).getId();
// 	}
// }
