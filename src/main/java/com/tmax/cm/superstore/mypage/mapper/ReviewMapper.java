package com.tmax.cm.superstore.mypage.mapper;

import com.tmax.cm.superstore.config.CommonMapperConfig;
import com.tmax.cm.superstore.mypage.dto.GetAllReviewForSellerResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetAllReviewResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetReviewResponseDto;
import com.tmax.cm.superstore.mypage.entity.Review;
import com.tmax.cm.superstore.mypage.entity.ReviewReply;
import com.tmax.cm.superstore.order.entity.OrderOption;
import com.tmax.cm.superstore.order.entity.OrderOptionGroup;
import com.tmax.cm.superstore.order.entity.PickupOrderItem;
import com.tmax.cm.superstore.order.entity.ShippingOrderItem;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = CommonMapperConfig.class)
public interface ReviewMapper {

    default GetAllReviewResponseDto.Review toAllShippingReviewDto(Review review, ShippingOrderItem shippingOrderItem){
        return GetAllReviewResponseDto.Review.builder()
            .id(review.getId())
            .orderType(review.getOrderType())
            .orderItem(this.toAllShippingOrderItem(shippingOrderItem, review))
            .content(review.getContent())
            .starRating(review.getStarRating())
            .createdAt(review.getCreatedAt())
            .build();
    }

    default GetReviewResponseDto.Review toShippingReviewDto(Review review, ShippingOrderItem shippingOrderItem){
        return GetReviewResponseDto.Review.builder()
            .id(review.getId())
            .orderType(review.getOrderType())
            .orderItem(this.toShippingOrderItem(shippingOrderItem, review))
            .content(review.getContent())
            .createdAt(review.getCreatedAt())
            .reviewReply(this.toReviewReply(review.getReviewReply()))
            .build();
    }

    // @Mapping(target = "id", source = "review.id")
    // @Mapping(target = "orderType", source = "review.orderType")
    // @Mapping(target = "orderItem", source = "review.orderItem")
    // @Mapping(target = "content", source = "review.content")
    // @Mapping(target = "createdAt", source = "review.createdAt")
    // // @Mapping(target = "reviewReply", source = "review.reviewReply")
    // default GetReviewResponseDto.Review toPickupReviewDto(Review review, PickupOrderItem pickupOrderItem){
    //     return GetReviewResponseDto.Review.builder()
    //         .id(review.getId())
    //         .orderType(review.getOrderType())
    //         .orderItem(this.toPickupOrderItem(pickupOrderItem, review))
    //         .content(review.getContent())
    //         .createdAt(review.getCreatedAt())
    //         .reviewReply(this.toReviewReply(review.getReviewReply()))
    //         .build();
    // }

    // @Mapping(target = "name", source = "shippingOrderItem.name")
    // @Mapping(target = "price", source = "shippingOrderItem.price")
    // @Mapping(target = "count", source = "shippingOrderItem.amount")
    // @Mapping(target = "orderOptionGroups", source = "review.")
    default GetReviewResponseDto.Review.OrderItem toShippingOrderItem(ShippingOrderItem shippingOrderItem, Review review){
        return GetReviewResponseDto.Review.OrderItem.builder()
            .name(shippingOrderItem.getName())
            .price(shippingOrderItem.getAmount())
            .count(shippingOrderItem.getAmount())
            .orderOptionGroups(this.toOrderOptionGroups(review.getShippingOrderSelectedOption().getOrderOptionGroups()))
            .build();
    }

    default GetAllReviewResponseDto.Review.OrderItem toAllShippingOrderItem(ShippingOrderItem shippingOrderItem, Review review){
        return GetAllReviewResponseDto.Review.OrderItem.builder()
            .name(shippingOrderItem.getName())
            .price(shippingOrderItem.getAmount())
            .count(shippingOrderItem.getAmount())
            .orderOptionGroups(this.toAllOrderOptionGroups(review.getShippingOrderSelectedOption().getOrderOptionGroups()))
            .build();
    }

    List<GetAllReviewResponseDto.Review.OrderItem.OrderOptionGroup> toAllOrderOptionGroups(List<OrderOptionGroup> orderOptionGroups);

    @Mapping(target = "name", source = "orderOptionGroup.name")
    @Mapping(target = "orderOptions", source = "orderOptionGroup.orderOptions")
    GetAllReviewResponseDto.Review.OrderItem.OrderOptionGroup toAllOrderOptionGroup(OrderOptionGroup orderOptionGroup);


    List<GetAllReviewResponseDto.Review.OrderItem.OrderOptionGroup.OrderOption> toAllOrderOptions(List<OrderOption> orderOption);

    @Mapping(target = "name", source = "orderOption.name")
    @Mapping(target = "count", source = "orderOption.count")
    @Mapping(target = "price", source = "orderOption.price")
    GetAllReviewResponseDto.Review.OrderItem.OrderOptionGroup.OrderOption toAllOrderOption(OrderOption orderOption);


    List<GetReviewResponseDto.Review.OrderItem.OrderOptionGroup> toOrderOptionGroups(List<OrderOptionGroup> orderOptionGroups);

    @Mapping(target = "name", source = "orderOptionGroup.name")
    @Mapping(target = "orderOptions", source = "orderOptionGroup.orderOptions")
    GetReviewResponseDto.Review.OrderItem.OrderOptionGroup toOrderOptionGroup(OrderOptionGroup orderOptionGroup);

    List<GetReviewResponseDto.Review.OrderItem.OrderOptionGroup.OrderOption> toOrderOptions(List<OrderOption> orderOption);

    @Mapping(target = "name", source = "orderOption.name")
    @Mapping(target = "count", source = "orderOption.count")
    @Mapping(target = "price", source = "orderOption.price")
    GetReviewResponseDto.Review.OrderItem.OrderOptionGroup.OrderOption toOrderOption(OrderOption orderOption);

    @Mapping(target = "content", source = "reviewReply.content")
    GetReviewResponseDto.Review.ReviewReply toReviewReply(ReviewReply reviewReply);

}
