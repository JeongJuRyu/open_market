package com.tmax.cm.superstore.mypage.mapper;

import com.tmax.cm.superstore.config.CommonMapperConfig;
import com.tmax.cm.superstore.mypage.dto.GetAllReviewForSellerResponseDto;
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

    List<GetAllReviewResponseDto.Review> toReviewsDto(List<Review> reviews);

    @Mapping(target = "id", source = "review.id")
    @Mapping(target = "title", source = "review.title")
    @Mapping(target = "content", source = "review.content")
    @Mapping(target = "reviewImages", source = "review.reviewImages")
    @Mapping(target = "reviewReply", source = "review.reviewReply")
    GetReviewResponseDto.Review toReviewDto(Review review);

    List<GetAllReviewResponseDto.Review.ReviewImage> toReviewImagesDto(List<ReviewImage> reviewImages);

    @Mapping(target = "url", source = "reviewImage.url")
    GetAllReviewResponseDto.Review.ReviewImage toReviewImageDto(ReviewImage reviewImage);

    @Mapping(target = "content", source = "reviewReply.content")
    GetReviewResponseDto.Review.ReviewReply toReviewReply(ReviewReply reviewReply);

    List<GetAllReviewForSellerResponseDto.Review> toReviewsForSellerDto(List<Review> reviews);

    @Mapping(target = "itemName", source = "review.item.id")
    GetAllReviewForSellerResponseDto.Review toReviewForSellerDto(Review review);

    List<GetAllReviewForSellerResponseDto.Review.ReviewImage> toReviewImagesForSellerDto(List<ReviewImage> reviewImages);

    GetAllReviewForSellerResponseDto.Review.ReviewImage toReviewImageForSellerDto(ReviewImage reviewImage);
}
