package com.tmax.cm.superstore.integrationtest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.epages.restdocs.apispec.ParameterDescriptorWithType;
import com.tmax.cm.superstore.EasyRestDocumentation;
import com.tmax.cm.superstore.code.PickupType;
import com.tmax.cm.superstore.code.ShippingType;

public class OrderBuyerControllerIntegrationTest extends AbstractIntegrationTest {

    private String tag = "Order Buyer";

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
    void testPostOrder() throws Exception {
        // given
        JSONObject request = new JSONObject() {
            {
                put("paymentType", "CREDIT_CARD");
                put("paymentAmount", 1000);
                put("cartItemIds", new JSONArray() {
                    {
                        put("b735da9e-b59a-4caf-80a9-2c894773e447");
                        put("46ac3f0d-c57f-4878-86ec-e0bea0a88fd6");
                    }
                });
                put("shippingRecipientInfo", new JSONObject() {
                    {
                        put("recipient", "김맥스");
                        put("address", "오리 연구소");
                        put("mobile", "010-1234-5678");
                        put("requests", "문 앞");
                    }
                });
                put("deliveryRecipientInfo", new JSONObject() {
                    {
                        put("recipient", "김맥스");
                        put("address", "미금 연구소");
                        put("mobile", "010-1234-5678");
                        put("requests", "문 앞");
                    }
                });
            }
        };

        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .post("/v1/order/buyer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request.toString())
                .header(HttpHeaders.AUTHORIZATION, this.testJwtGenerator.generate()));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.documentWithJwt("postOrder", "주문하기", this.tag));
    }

    @Test
    void testGetVisitAndPickupOrderSelectedOptionAllByUser() throws Exception {
        // given

        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .get("/v1/order/buyer/visitAndPickup")
                .header(HttpHeaders.AUTHORIZATION, this.testJwtGenerator.generate()));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.documentWithJwt(
                        "getVisitAndPickupOrderSelectedOptionAllByUser",
                        "유저별 방문수령 및 픽업 주문 조회", this.tag));
    }

    @Test
    void testGetVisitAndPickupOrderSelectedOptionAllByUserAndPickupTypePICKUP_WAITING() throws Exception {
        // given
        PickupType pickupType = PickupType.PICKUP_WAITING;

        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .get("/v1/order/buyer/visitAndPickup")
                .param("pickupType", pickupType.toString())
                .header(HttpHeaders.AUTHORIZATION, this.testJwtGenerator.generate()));

        // then
        ParameterDescriptorWithType requestParameterPickupType = EasyRestDocumentation
                .createRequestParameter("pickupType", this.createTypeDescription(PickupType.class),
                        false);

        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.documentWithJwtAndRequestParameter(
                        "getVisitAndPickupOrderSelectedOptionAllByUserAndPickupTypePICKUP_WAITING",
                        "유저별 방문수령 및 픽업 주문 조회",
                        this.tag, requestParameterPickupType));
    }

    @Test
    void testGetVisitAndPickupOrderSelectedOptionAllByUserAndPickupTypePICKUP_READY() throws Exception {
        // given
        PickupType pickupType = PickupType.PICKUP_READY;

        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .get("/v1/order/buyer/visitAndPickup")
                .param("pickupType", pickupType.toString())
                .header(HttpHeaders.AUTHORIZATION, this.testJwtGenerator.generate()));

        // then
        ParameterDescriptorWithType requestParameterPickupType = EasyRestDocumentation
                .createRequestParameter("pickupType", this.createTypeDescription(PickupType.class),
                        false);

        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.documentWithJwtAndRequestParameter(
                        "getVisitAndPickupOrderSelectedOptionAllByUserAndPickupTypePICKUP_READY",
                        "유저별 방문수령 및 픽업 주문 조회",
                        this.tag, requestParameterPickupType));
    }

    @Test
    void testGetShippingAndDeliveryOrderSelectedOptionAllByUser() throws Exception {
        // given

        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .get("/v1/order/buyer/shippingAndDelivery")
                .header(HttpHeaders.AUTHORIZATION, this.testJwtGenerator.generate()));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.documentWithJwt(
                        "getShippingAndDeliveryOrderSelectedOptionAllByUser",
                        "유저별 배송 및 배달 주문 조회", this.tag));
    }

    @Test
    void testGetShippingAndDeliveryOrderSelectedOptionAllByUserAndShippingTypeShipping_WAITING() throws Exception {
        // given
        ShippingType shippingType = ShippingType.SHIPPING_WAITING;

        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .get("/v1/order/buyer/shippingAndDelivery", this.sellerId)
                .param("shippingType", shippingType.toString())
                .header(HttpHeaders.AUTHORIZATION, this.testJwtGenerator.generate()));

        // then
        ParameterDescriptorWithType requestParameterPickupType = EasyRestDocumentation
                .createRequestParameter("shippingType", this.createTypeDescription(ShippingType.class),
                        false);

        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.documentWithJwtAndRequestParameter(
                        "getShippingAndDeliveryOrderSelectedOptionAllByUserAndShippingTypeShipping_WAITING",
                        "유저별 배송 및 배달 주문 조회",
                        this.tag, requestParameterPickupType));
    }
}
