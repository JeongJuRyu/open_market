package com.tmax.cm.superstore.integrationtest;

import org.junit.jupiter.api.Test;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.epages.restdocs.apispec.ParameterDescriptorWithType;
import com.tmax.cm.superstore.EasyRestDocumentation;
import com.tmax.cm.superstore.code.PickupType;
import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.code.ShippingType;

public class OrderSellerControllerIntegrationTest extends AbstractIntegrationTest {

    private String tag = "Order Seller";

    private String sellerId = "2d68d1d0-ed27-46d2-b858-da3f0aa2e430";

    <T extends Enum<T>> String createTypeDescription(Class<T> enumClass) {
        StringBuilder typeDescriptionBuilder = new StringBuilder("상태에 따른 필터링 [ ");

        for (T type : enumClass.getEnumConstants()) {
            typeDescriptionBuilder.append(type.toString()).append(", ");
        }
        typeDescriptionBuilder.delete(typeDescriptionBuilder.length() - 2, typeDescriptionBuilder.length());
        typeDescriptionBuilder.append(" ]");

        return typeDescriptionBuilder.toString();
    }

    @Test
    void testGetVisitAndPickupOrderSelectedOptionAllByShop() throws Exception {
        // given

        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .get("/v1/order/seller/{sellerId}/visitAndPickup", this.sellerId));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.document(
                        "getVisitAndPickupOrderSelectedOptionAllByShop",
                        "가게별 방문수령 및 픽업 주문 조회", this.tag));
    }

    @Test
    void testGetVisitAndPickupOrderSelectedOptionAllByShopAndPickupTypePICKUP_WAITING() throws Exception {
        // given
        PickupType pickupType = PickupType.PICKUP_WAITING;

        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .get("/v1/order/seller/{sellerId}/visitAndPickup", this.sellerId)
                .param("pickupType", pickupType.toString()));

        // then
        ParameterDescriptorWithType requestParameterPickupType = EasyRestDocumentation
                .createRequestParameter("pickupType", this.createTypeDescription(PickupType.class),
                        false);

        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.documentWithRequestParameter(
                        "getVisitAndPickupOrderSelectedOptionAllByShopAndPickupTypePICKUP_WAITING",
                        "가게별 방문수령 및 픽업 주문 조회",
                        this.tag, requestParameterPickupType));
    }

    @Test
    void testGetVisitAndPickupOrderSelectedOptionAllByShopAndPickupTypePICKUP_READY() throws Exception {
        // given
        PickupType pickupType = PickupType.PICKUP_READY;

        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .get("/v1/order/seller/{sellerId}/visitAndPickup", this.sellerId)
                .param("pickupType", pickupType.toString()));

        // then
        ParameterDescriptorWithType requestParameterPickupType = EasyRestDocumentation
                .createRequestParameter("pickupType", this.createTypeDescription(PickupType.class),
                        false);

        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.documentWithRequestParameter(
                        "getVisitAndPickupOrderSelectedOptionAllByShopAndPickupTypePICKUP_READY",
                        "가게별 방문수령 및 픽업 주문 조회",
                        this.tag, requestParameterPickupType));
    }

    @Test
    void testGetShippingAndDeliveryOrderSelectedOptionAllByShop() throws Exception {
        // given

        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .get("/v1/order/seller/{sellerId}/shippingAndDelivery", this.sellerId));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.document(
                        "getShippingAndDeliveryOrderSelectedOptionAllByShop",
                        "가게별 배송 및 배달 주문 조회", this.tag));
    }

    @Test
    void testGetShippingAndDeliveryOrderSelectedOptionAllByShopAndShippingTypeShipping_WAITING() throws Exception {
        // given
        ShippingType shippingType = ShippingType.SHIPPING_WAITING;

        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .get("/v1/order/seller/{sellerId}/shippingAndDelivery", this.sellerId)
                .param("shippingType", shippingType.toString()));

        // then
        ParameterDescriptorWithType requestParameterPickupType = EasyRestDocumentation
                .createRequestParameter("shippingType", this.createTypeDescription(ShippingType.class),
                        false);

        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.documentWithRequestParameter(
                        "getShippingAndDeliveryOrderSelectedOptionAllByShopAndShippingTypeShipping_WAITING",
                        "가게별 배송 및 배달 주문 조회",
                        this.tag, requestParameterPickupType));
    }

    @Test
    void testPutAcceptShipping() throws Exception {
        // given
        String selectedOptionId = "9c937a70-12cf-4a83-b594-1293b3f994a8";

        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .put("/v1/order/seller/{sellerId}/shippingAndDelivery/{selectedOptionId}/acceptShipping",
                        this.sellerId, selectedOptionId));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.document("acceptShipping",
                        ResponseCode.ORDER_ACCEPT_SHIPPING.getDescription()
                                + "\n`selectedOptionId` = 9c937a70-12cf-4a83-b594-1293b3f994a8",
                        this.tag));
    }

    @Test
    void testPutRejectShipping() throws Exception {
        // given
        String selectedOptionId = "9c937a70-12cf-4a83-b594-1293b3f994a8";

        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .put("/v1/order/seller/{sellerId}/shippingAndDelivery/{selectedOptionId}/rejectShipping",
                        this.sellerId, selectedOptionId));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.document("rejectShipping",
                        ResponseCode.ORDER_REJECT_SHIPPING.getDescription()
                                + "\n`selectedOptionId` = 9c937a70-12cf-4a83-b594-1293b3f994a8",
                        this.tag));
    }

    @Test
    void testPutDoneShipping() throws Exception {
        // given
        String selectedOptionId = "9c937a70-12cf-4a83-b594-1293b3f994a8";

        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .put("/v1/order/seller/{sellerId}/shippingAndDelivery/{selectedOptionId}/doneShipping",
                        this.sellerId, selectedOptionId));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(EasyRestDocumentation.document("doneShipping",
                        ResponseCode.ORDER_DONE_SHIPPING.getDescription()
                                + "\n`selectedOptionId` = 9c937a70-12cf-4a83-b594-1293b3f994a8",
                        this.tag));
    }

    @Test
    void testPutAcceptPick() throws Exception {
        // given
        String selectedOptionId = "9c937a70-12cf-4a83-b594-1293b3f994a8";

        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .put("/v1/order/seller/{sellerId}/visitAndPickup/{selectedOptionId}/acceptPick",
                        this.sellerId, selectedOptionId));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(EasyRestDocumentation.document("acceptPick",
                        ResponseCode.ORDER_ACCEPT_PICK.getDescription()
                                + "\n`selectedOptionId` = 9c937a70-12cf-4a83-b594-1293b3f994a8",
                        this.tag));
    }

    @Test
    void testPutReadyPick() throws Exception {
        // given
        String selectedOptionId = "9c937a70-12cf-4a83-b594-1293b3f994a8";

        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .put("/v1/order/seller/{sellerId}/visitAndPickup/{selectedOptionId}/readyPick",
                        this.sellerId, selectedOptionId));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(EasyRestDocumentation.document("readyPick",
                        ResponseCode.ORDER_READY_PICK.getDescription()
                                + "\n`selectedOptionId` = 9c937a70-12cf-4a83-b594-1293b3f994a8",
                        this.tag));
    }

    @Test
    void testPutRefusePick() throws Exception {
        // given
        String selectedOptionId = "9c937a70-12cf-4a83-b594-1293b3f994a8";

        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .put("/v1/order/seller/{sellerId}/visitAndPickup/{selectedOptionId}/refusePick",
                        this.sellerId, selectedOptionId));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(EasyRestDocumentation.document("refusePick",
                        ResponseCode.ORDER_REFUSE_PICK.getDescription()
                                + "\n`selectedOptionId` = 9c937a70-12cf-4a83-b594-1293b3f994a8",
                        this.tag));
    }

    @Test
    void testPutDonePick() throws Exception {
        // given
        String selectedOptionId = "9c937a70-12cf-4a83-b594-1293b3f994a8";

        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .put("/v1/order/seller/{sellerId}/visitAndPickup/{selectedOptionId}/donePick",
                        this.sellerId, selectedOptionId));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(EasyRestDocumentation.document("donePick",
                        ResponseCode.ORDER_DONE_PICK.getDescription()
                                + "\n`selectedOptionId` = 9c937a70-12cf-4a83-b594-1293b3f994a8",
                        this.tag));
    }
}
