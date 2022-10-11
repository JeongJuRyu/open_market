package com.tmax.cm.superstore.mypage.mapper;

import com.tmax.cm.superstore.config.CommonMapperConfig;
import com.tmax.cm.superstore.mypage.dto.GetAllReviewForSellerResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetAllReviewResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetReviewResponseDto;
import com.tmax.cm.superstore.mypage.entity.Review;
import com.tmax.cm.superstore.mypage.entity.ReviewReply;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = CommonMapperConfig.class)
public interface ReviewMapper {

    List<GetAllReviewResponseDto.Review> toReviewsDto(List<Review> reviews);

    @Mapping(target = "id", source = "review.id")
    @Mapping(target = "content", source = "review.content")
    @Mapping(target = "reviewReply", source = "review.reviewReply")
    GetReviewResponseDto.Review toReviewDto(Review review);



    @Mapping(target = "content", source = "reviewReply.content")
    GetReviewResponseDto.Review.ReviewReply toReviewReply(ReviewReply reviewReply);

    List<GetAllReviewForSellerResponseDto.Review> toReviewsForSellerDto(List<Review> reviews);

    @Mapping(target = "itemName", source = "review.item.id")
    GetAllReviewForSellerResponseDto.Review toReviewForSellerDto(Review review);

}
