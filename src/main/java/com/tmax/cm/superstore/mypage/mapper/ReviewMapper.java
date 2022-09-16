package com.tmax.cm.superstore.mypage.mapper;

import com.tmax.cm.superstore.config.CommonMapperConfig;
import com.tmax.cm.superstore.mypage.dto.GetAllReviewResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetReviewResponseDto;
import com.tmax.cm.superstore.mypage.entity.Review;
import com.tmax.cm.superstore.mypage.entity.ReviewImage;
import com.tmax.cm.superstore.mypage.entity.ReviewReply;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = CommonMapperConfig.class)
public interface ReviewMapper {

    @Mapping(target = "id", source = "review.id")
    @Mapping(target = "title", source = "review.title")
    @Mapping(target = "content", source = "review.content")
    @Mapping(target = "createdAt", source = "review.createdAt")
    @Mapping(target = "reviewImages", source = "review.reviewImages")
    List<GetAllReviewResponseDto.ReviewDto> toReviewDto(List<Review> review);

    @Mapping(target = "url", source = "reviewImage.url")
    GetAllReviewResponseDto.ReviewDto.ReviewImage toReviewImageDto(ReviewImage reviewImage);

    @Mapping(target = "id", source = "review.id")
    @Mapping(target = "title", source = "review.title")
    @Mapping(target = "content", source = "review.content")
    GetReviewResponseDto.Review toReviewDto(Review review);

    /*@Mapping(target = "url", source = "reviewImage.url")
    GetReviewResponseDto.Review.ReviewImage toReviewImage(ReviewImage reviewImage);

    @Mapping(target = "content", source = "reviewReply.content")
    GetReviewResponseDto.Review.ReviewReply toReviewReply(ReviewReply reviewReply);*/
}
