package com.tmax.cm.superstore.integrationtest.data;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.tmax.cm.superstore.cart.entity.Cart;
import com.tmax.cm.superstore.cart.entity.CartItem;
import com.tmax.cm.superstore.cart.entity.CartOption;
import com.tmax.cm.superstore.cart.entity.CartOptionGroup;
import com.tmax.cm.superstore.cart.entity.SelectedOption;
import com.tmax.cm.superstore.cart.repository.CartRepository;
import com.tmax.cm.superstore.code.CartType;
import com.tmax.cm.superstore.code.SendType;
import com.tmax.cm.superstore.item.entity.Item;
import com.tmax.cm.superstore.item.entity.ItemSendType;
import com.tmax.cm.superstore.item.entity.Option;
import com.tmax.cm.superstore.item.entity.OptionGroup;
import com.tmax.cm.superstore.item.repository.ItemRepository;
import com.tmax.cm.superstore.shop.entity.Shop;
import com.tmax.cm.superstore.shop.repository.ShopRepository;

@TestConfiguration
public class IntegrationTestDataLoader {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ShopRepository shopRepository;

    @Bean
    public IntegrationTestData integrationTestData() {

        IntegrationTestData data = new IntegrationTestData();

        Shop shop = Shop.builder()
                .name("써머슈슈즈")
                .build();

        this.shopRepository.save(shop);

        Item item = Item.builder()
                .shop(shop)
                .name("로토 스르르트 썸머 슈즈")
                .price(81000)
                .optionGroups(new ArrayList<>())
                .itemSendTypes(new ArrayList<>())
                .build();

        ItemSendType itemSendType1 = ItemSendType.builder()
                .sendType(SendType.SHIPPING)
                .item(item)
                .build();

        item.getItemSendTypes().add(itemSendType1);

        ItemSendType itemSendType2 = ItemSendType.builder()
                .sendType(SendType.VISIT)
                .item(item)
                .build();

        item.getItemSendTypes().add(itemSendType2);

        OptionGroup optionGroup1 = OptionGroup.builder()
                .name("color")
                .isNecessary(true)
                .options(new ArrayList<>())
                .item(item)
                .build();

        Option option1 = Option.builder()
                .name("블랙")
                .price(0)
                .description("참을 수 없는 옵션의 유혹")
                .optionGroup(optionGroup1)
                .build();

        optionGroup1.getOptions().add(option1);

        Option option2 = Option.builder()
                .name("화이트")
                .price(0)
                .description("참을 수 없는 옵션의 유혹")
                .optionGroup(optionGroup1)
                .build();

        optionGroup1.getOptions().add(option2);

        item.getOptionGroups().add(optionGroup1);

        OptionGroup optionGroup2 = OptionGroup.builder()
                .name("굽 선택")
                .isNecessary(true)
                .options(new ArrayList<>())
                .item(item)
                .build();

        Option option3 = Option.builder()
                .name("3cm")
                .price(1000)
                .description("참을 수 없는 옵션의 유혹")
                .optionGroup(optionGroup2)
                .build();

        optionGroup2.getOptions()
                .add(option3);

        Option option4 = Option.builder()
                .name("5cm")
                .price(0)
                .description("참을 수 없는 옵션의 유혹")
                .optionGroup(optionGroup2)
                .build();

        optionGroup2.getOptions().add(option4);

        item.getOptionGroups().add(optionGroup2);

        this.itemRepository.save(item);

        data.setItemId(item.getId());
        data.setOptionGroupId(optionGroup1.getId());
        data.setOptionId(option1.getId());
        data.setShopId(shop.getId());

        Cart cart = Cart.builder()
                .cartType(CartType.SHIPPING_VISIT)
                .cartItems(new ArrayList<>())
                .build();

        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .item(item)
                .selectedOptions(new ArrayList<>())
                .sendType(SendType.SHIPPING)
                .build();

        cart.getCartItems().add(cartItem);

        SelectedOption selectedOption = SelectedOption.builder()
                .cartItem(cartItem)
                .cartOptionGroups(new ArrayList<>())
                .count(1)
                .build();

        cartItem.getSelectedOptions().add(selectedOption);

        CartOptionGroup cartOptionGroup = CartOptionGroup.builder()
                .selectedOption(selectedOption)
                .cartOptions(new ArrayList<>())
                .optionGroup(optionGroup1)
                .build();

        selectedOption.getCartOptionGroups().add(cartOptionGroup);

        CartOption cartOption = CartOption.builder()
                .cartOptionGroup(cartOptionGroup)
                .count(1)
                .option(option1)
                .build();

        cartOptionGroup.getCartOptions().add(cartOption);

        this.cartRepository.save(cart);

        data.setCartItemId(cartItem.getId());
        data.setSelectedOptionId(selectedOption.getId());
        data.setCartOptionGroupId(cartOptionGroup.getId());
        data.setCartOptionId(cartOption.getId());

        return data;
    }
}
