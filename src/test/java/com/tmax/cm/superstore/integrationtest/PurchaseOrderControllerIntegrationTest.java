package com.tmax.cm.superstore.integrationtest;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.tmax.cm.superstore.EasyRestDocumentation;

@Transactional
@SpringBootTest
@ExtendWith(RestDocumentationExtension.class)
@ActiveProfiles("develop-integration-test")
public class PurchaseOrderControllerIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    private String tag = "Purchase Order";

    @BeforeEach
    public void setUp(RestDocumentationContextProvider restDocumentation) throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .apply(MockMvcRestDocumentation.documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    void testPostPurchaseOrderCart() throws Exception {
        // given
        JSONObject request = new JSONObject() {
            {
                put("cartItemIds", new JSONArray() {
                    {
                        put("b735da9e-b59a-4caf-80a9-2c894773e447");
                        put("46ac3f0d-c57f-4878-86ec-e0bea0a88fd6");
                    }
                });
            }
        };

        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .post("/v1/purchaseOrder/cart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request.toString()));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.document("postPurchaseOrderCart", "장바구니에서 주문 화면으로 전환", this.tag));
    }

    @Test
    void testPostPurchaseOrderBuyNow() throws Exception {
        // given
        JSONObject request = new JSONObject() {
            {
                put("itemId", "169f84f8-8862-477c-ad27-0b79871deb27");
                put("sendType", "shipping");
                put("selectedOptions", new JSONArray() {
                    {
                        put(new JSONObject() {
                            {
                                put("count", 1);
                                put("cartOptionGroups", new JSONArray() {
                                    {
                                        put(new JSONObject() {
                                            {
                                                put("optionGroupId", "62cfa7ab-26f5-46cf-af80-f9dedfda5693");
                                                put("cartOptions", new JSONArray() {
                                                    {
                                                        put(new JSONObject() {
                                                            {
                                                                put("optionId", "d8b52a5a-45e7-424a-beaa-7f281081f1c6");
                                                                put("count", 1);
                                                            }
                                                        });
                                                    }
                                                });
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
            }
        };

        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .post("/v1/purchaseOrder/buyNow")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request.toString()));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.document("postPurchaseOrderBuyNow", "바로 구매로 주문 화면으로 전환", this.tag));
    }
}