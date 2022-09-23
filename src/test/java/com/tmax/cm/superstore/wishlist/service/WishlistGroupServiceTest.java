//package com.tmax.cm.superstore.wishlist.service;
//
//import com.tmax.cm.superstore.wishlist.entity.WishlistGroup;
//import com.tmax.cm.superstore.wishlist.entity.WishlistItem;
//import com.tmax.cm.superstore.wishlist.repository.WishlistGroupRepository;
//import org.hibernate.service.spi.InjectService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(MockitoExtension.class)
//class WishlistGroupServiceTest {
//
//    @InjectMocks
//    private WishlistGroupService wishlistGroupService;
//    private WishlistItemService wishlistItemService;
//
//    @Mock
//    private WishlistGroupRepository wishlistGroupRepository;
//
//    @Test
//    public void 그룹삭제() throws Exception {
//        WishlistGroup wishlistGroup = WishlistGroup.builder()
//                .name("테스트용")
//                .position(3)
//                .build();
//
//        WishlistItem wishlistItem1 = WishlistItem.builder().build();
//        WishlistItem wishlistItem2 = WishlistItem.builder().build();
//
//        wishlistItem1.setGroup(wishlistGroup);
//        wishlistItem2.setGroup(wishlistGroup);
//
//        Assertions.assertEquals(wishlistGroup.getWishlistItems().size(), 2);
//
////        wishlistGroup.deleteGroup();
////        Assertions.assertEquals(wishlistGroup.getWishlistItems().size(), 0);
////        Assertions.assertEquals(wishlistItem1, null);
////        Assertions.assertEquals(wishlistItem2, null);
//    }
//
//}