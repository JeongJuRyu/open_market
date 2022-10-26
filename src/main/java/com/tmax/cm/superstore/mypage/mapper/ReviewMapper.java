package com.tmax.cm.superstore.mypage.mapper;

import com.tmax.cm.superstore.config.CommonMapperConfig;
import com.tmax.cm.superstore.item.entity.Item;
import com.tmax.cm.superstore.mypage.dto.GetAllReviewForSellerResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetAllReviewResponseDto;
import com.tmax.cm.superstore.mypage.dto.GetReviewResponseDto;
import com.tmax.cm.superstore.mypage.entity.Review;
import com.tmax.cm.superstore.mypage.entity.ReviewReply;
import com.tmax.cm.superstore.order.entity.Order;
import com.tmax.cm.superstore.order.entity.OrderOption;
import com.tmax.cm.superstore.order.entity.OrderOptionGroup;
import com.tmax.cm.superstore.order.entity.PickupOrderItem;
import com.tmax.cm.superstore.order.entity.ShippingOrderItem;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(config = CommonMapperConfig.class)
public interface ReviewMapper {

    default GetAllReviewForSellerResponseDto.Review toAllReviewForSellerDto(Review review, Order order){
        Item item = review.getItem();
        return GetAllReviewForSellerResponseDto.Review.builder()
            .reviewId(review.getId())
            .itemName(item.getName())
            .orderItemId(item.getId())
            .content(review.getContent())
            .createdAt(review.getCreatedAt())
            .modifiedAt(review.getModifiedAt())
            .starRating(review.getStarRating())
            .orderId(order.getId())
            .build();
    }
    default GetAllReviewResponseDto.Review toAllShippingReviewDto(Review review, ShippingOrderItem shippingOrderItem, Item item, UUID selectedOptionId){
        return GetAllReviewResponseDto.Review.builder()
            .reviewId(review.getId())
            .orderType(review.getOrderType())
            .orderItem(this.toAllShippingOrderItem(shippingOrderItem, review, item, selectedOptionId))
            .content(review.getContent())
            .starRating(review.getStarRating())
            .createdAt(review.getCreatedAt())
            .build();
    }

    default GetReviewResponseDto.Review toShippingReviewDto(Review review, ShippingOrderItem shippingOrderItem, Item item, String itemImageId){
        return GetReviewResponseDto.Review.builder()
            .reviewId(review.getId())
            .orderType(review.getOrderType())
            .orderItem(this.toShippingOrderItem(shippingOrderItem, review, item, itemImageId))
            .content(review.getContent())
            .starRating(review.getStarRating())
            .createdAt(review.getCreatedAt())
            .reviewReply(this.toReviewReply(review.getReviewReply()))
            .build();
    }

    default GetAllReviewResponseDto.Review toAllPickupReviewDto(Review review, PickupOrderItem pickupOrderItem, Item item, UUID selectedOptionId){
        return GetAllReviewResponseDto.Review.builder()
            .reviewId(review.getId())
            .orderType(review.getOrderType())
            .orderItem(this.toAllPickupOrderItem(pickupOrderItem, review, item, selectedOptionId))
            .content(review.getContent())
            .starRating(review.getStarRating())
            .createdAt(review.getCreatedAt())
            .build();
    }

    default GetReviewResponseDto.Review toPickupReviewDto(Review review, PickupOrderItem pickupOrderItem, Item item, String itemImageId){
        return GetReviewResponseDto.Review.builder()
            .reviewId(review.getId())
            .orderType(review.getOrderType())
            .orderItem(this.toPickupOrderItem(pickupOrderItem, review, item, itemImageId))
            .content(review.getContent())
            .starRating(review.getStarRating())
            .createdAt(review.getCreatedAt())
            .reviewReply(this.toReviewReply(review.getReviewReply()))
            .build();
    }
    default GetAllReviewResponseDto.Review.OrderItem toAllShippingOrderItem(ShippingOrderItem shippingOrderItem, Review review, Item item, UUID selectedOptionId){
        String ItemImageId = item.getItemImages().size() != 0 ? item.getItemImages().get(0).getFileId() : null;
        return GetAllReviewResponseDto.Review.OrderItem.builder()
            .itemId(item.getId())
            .itemName(shippingOrderItem.getName())
            .itemImageId(ItemImageId)
            .orderSelectedOptionId(selectedOptionId)
            .price(shippingOrderItem.getAmount())
            .count(1)
            .orderOptionGroups(this.toAllOrderOptionGroups(review.getShippingOrderSelectedOption().getOrderOptionGroups()))
            .build();
    }
    default GetReviewResponseDto.Review.OrderItem toShippingOrderItem(ShippingOrderItem shippingOrderItem, Review review, Item item, String itemImageId){
        return GetReviewResponseDto.Review.OrderItem.builder()
            .itemName(shippingOrderItem.getName())
            .itemId(item.getId())
            .itemImageId(itemImageId)
            .price(shippingOrderItem.getAmount())
            .count(1)
            .orderOptionGroups(this.toOrderOptionGroups(review.getShippingOrderSelectedOption().getOrderOptionGroups()))
            .build();
    }
    default GetAllReviewResponseDto.Review.OrderItem toAllPickupOrderItem(PickupOrderItem pickupOrderItem, Review review, Item item, UUID selectedOptionId){
        String ItemImageId = item.getItemImages().size() != 0 ? item.getItemImages().get(0).getFileId() : null;
        return GetAllReviewResponseDto.Review.OrderItem.builder()
            .itemId(item.getId())
            .itemName(pickupOrderItem.getName())
            .itemImageId(ItemImageId)
            .orderSelectedOptionId(selectedOptionId)
            .price(pickupOrderItem.getAmount())
            .count(1)
            .orderOptionGroups(this.toAllOrderOptionGroups(review.getPickupOrderSelectedOption().getOrderOptionGroups()))
            .build();
    }

    default GetReviewResponseDto.Review.OrderItem toPickupOrderItem(PickupOrderItem pickupOrderItem, Review review, Item item, String itemImageId){
        return GetReviewResponseDto.Review.OrderItem.builder()
            .itemName(pickupOrderItem.getName())
            .itemId(item.getId())
            .itemImageId(itemImageId)
            .price(pickupOrderItem.getAmount())
            .count(1)
            .orderOptionGroups(this.toOrderOptionGroups(review.getPickupOrderSelectedOption().getOrderOptionGroups()))
            .build();
    }



    List<GetAllReviewResponseDto.Review.OrderItem.OrderOptionGroup> toAllOrderOptionGroups(List<OrderOptionGroup> orderOptionGroups);

    @Mapping(target = "optionGroupName", source = "orderOptionGroup.name")
    @Mapping(target = "orderOptions", source = "orderOptionGroup.orderOptions")
    GetAllReviewResponseDto.Review.OrderItem.OrderOptionGroup toAllOrderOptionGroup(OrderOptionGroup orderOptionGroup);


    List<GetAllReviewResponseDto.Review.OrderItem.OrderOptionGroup.OrderOption> toAllOrderOptions(List<OrderOption> orderOption);

    @Mapping(target = "optionName", source = "orderOption.name")
    @Mapping(target = "count", source = "orderOption.count")
    @Mapping(target = "price", source = "orderOption.price")
    GetAllReviewResponseDto.Review.OrderItem.OrderOptionGroup.OrderOption toAllOrderOption(OrderOption orderOption);


    List<GetReviewResponseDto.Review.OrderItem.OrderOptionGroup> toOrderOptionGroups(List<OrderOptionGroup> orderOptionGroups);

    @Mapping(target = "optionGroupName", source = "orderOptionGroup.name")
    @Mapping(target = "orderOptions", source = "orderOptionGroup.orderOptions")
    GetReviewResponseDto.Review.OrderItem.OrderOptionGroup toOrderOptionGroup(OrderOptionGroup orderOptionGroup);

    List<GetReviewResponseDto.Review.OrderItem.OrderOptionGroup.OrderOption> toOrderOptions(List<OrderOption> orderOption);

    @Mapping(target = "optionName", source = "orderOption.name")
    @Mapping(target = "count", source = "orderOption.count")
    @Mapping(target = "price", source = "orderOption.price")
    GetReviewResponseDto.Review.OrderItem.OrderOptionGroup.OrderOption toOrderOption(OrderOption orderOption);

    @Mapping(target = "content", source = "reviewReply.content")
    GetReviewResponseDto.Review.ReviewReply toReviewReply(ReviewReply reviewReply);

}
