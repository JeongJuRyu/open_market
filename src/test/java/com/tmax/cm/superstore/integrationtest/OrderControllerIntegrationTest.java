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

import com.tmax.cm.superstore.EasyRestDocumentation;

public class OrderControllerIntegrationTest extends AbstractIntegrationTest {

    private String tag = "Order";

    private String sellerId = "2d68d1d0-ed27-46d2-b858-da3f0aa2e430";

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
                .post("/v1/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request.toString())
                .header(HttpHeaders.AUTHORIZATION, this.testJwtGenerator.generate()));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.documentWithJwt("postOrder", "주문하기", this.tag));
    }

    @Test
    void testGetVisitAndPickupOrderSelectedOptionAll() throws Exception {
        // given

        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .get("/v1/order/visitAndPickup/seller/{sellerId}", this.sellerId)
                .header(HttpHeaders.AUTHORIZATION, this.testJwtGenerator.generate()));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.documentWithJwt("getVisitAndPickupOrderSelectedOptionAll", "가게별 방문수령 및 픽업 주문 조회", this.tag));
    }

    @Test
    void testGetShippingAndDeliveryOrderSelectedOptionAll() throws Exception {
        // given

        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .get("/v1/order/shippingAndDelivery/seller/{sellerId}", this.sellerId)
                .header(HttpHeaders.AUTHORIZATION, this.testJwtGenerator.generate()));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.documentWithJwt("getShippingAndDeliveryOrderSelectedOptionAll", "가게별 배송 및 배달 주문 조회", this.tag));
    }
}
