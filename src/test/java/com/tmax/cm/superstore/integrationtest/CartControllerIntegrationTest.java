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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.tmax.cm.superstore.EasyRestDocumentation;
import com.tmax.cm.superstore.integrationtest.data.IntegrationTestData;
import com.tmax.cm.superstore.integrationtest.data.IntegrationTestDataLoader;

@Transactional
@SpringBootTest
@ExtendWith(RestDocumentationExtension.class)
@ActiveProfiles("develop-integration-test")
@ContextConfiguration(classes = {
        IntegrationTestDataLoader.class
})
public class CartControllerIntegrationTest {

    @Autowired
    private IntegrationTestData testData;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    private String tag = "Cart";

    @BeforeEach
    public void setUp(RestDocumentationContextProvider restDocumentation) throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .apply(MockMvcRestDocumentation.documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    void testPostItem() throws Exception {
        // given
        JSONObject request = new JSONObject() {
            {
                put("itemId", testData.getItemId());
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
                                                put("optionGroupId", testData.getOptionGroupId());
                                                put("cartOpions", new JSONArray() {{
                                                    put(new JSONObject() {{
                                                        
                                                        put("optionId", testData.getOptionId());
                                                        put("count", 1);
                                                    }});
                                                }});
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
                .post("/v1/cart/cartItem")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request.toString()));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.document("postCartItem", "카트 상품 생성", this.tag));
    }

    @Test
    void testCart() throws Exception {
        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .get("/v1/cart"));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.document("getCart", "카트 조회", this.tag));
    }
}
