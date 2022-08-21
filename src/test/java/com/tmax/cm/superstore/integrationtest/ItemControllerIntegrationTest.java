package com.tmax.cm.superstore.integrationtest;

import java.util.UUID;

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
import org.springframework.test.web.servlet.MvcResult;
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
public class ItemControllerIntegrationTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    private String tag = "Item";

    private UUID itemId;

    @BeforeEach
    public void setUp(RestDocumentationContextProvider restDocumentation) throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .apply(MockMvcRestDocumentation.documentationConfiguration(restDocumentation))
                .build();

        {
            JSONObject createItemRequest = new JSONObject() {
                {
                    put("shopName", "서머슈슈즈");
                    put("name", "로토 스르르트 썸머 슈즈");
                    put("price", 82000);
                    put("possibleSendType", new JSONArray() {
                        {
                            put("shipping");
                        }
                    });
                    put("optionGroups", new JSONArray() {
                        {
                            put(new JSONObject() {
                                {
                                    put("name", "color");
                                    put("isNecessary", true);
                                    put("options", new JSONArray() {
                                        {
                                            put(new JSONObject() {
                                                {
                                                    put("name", "블랙");
                                                    put("price", 0);
                                                }
                                            });
                                            put(new JSONObject() {
                                                {
                                                    put("name", "화이트");
                                                    put("price", 0);
                                                }
                                            });
                                        }
                                    });
                                }
                            });
                            put(new JSONObject() {
                                {
                                    put("name", "굽 변경");
                                    put("isNecessary", false);
                                    put("options", new JSONArray() {
                                        {
                                            put(new JSONObject() {
                                                {
                                                    put("name", "3cm");
                                                    put("price", 1000);
                                                }
                                            });
                                            put(new JSONObject() {
                                                {
                                                    put("name", "5cm");
                                                    put("price", 0);
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
            MvcResult createItemResult = this.mvc.perform(RestDocumentationRequestBuilders
                    .post("/v1/item")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(createItemRequest.toString()))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();

            JSONObject createItemResponse = new JSONObject(
                    createItemResult.getResponse().getContentAsString());
            this.itemId = UUID.fromString(createItemResponse.getJSONObject("data").getString("itemId"));
        }

        {

        }
    }

    @Test
    void testPostItem() throws Exception {
        // given
        JSONObject request = new JSONObject() {
            {
                put("shopName", "서머슈슈즈");
                put("name", "로토 스르르트 썸머 슈즈");
                put("price", 82000);
                put("possibleSendType", new JSONArray() {
                    {
                        put("shipping");
                    }
                });
                put("optionGroups", new JSONArray() {
                    {
                        put(new JSONObject() {
                            {
                                put("name", "color");
                                put("isNecessary", true);
                                put("options", new JSONArray() {
                                    {
                                        put(new JSONObject() {
                                            {
                                                put("name", "블랙");
                                                put("price", 0);
                                            }
                                        });
                                        put(new JSONObject() {
                                            {
                                                put("name", "화이트");
                                                put("price", 0);
                                            }
                                        });
                                    }
                                });
                            }
                        });
                        put(new JSONObject() {
                            {
                                put("name", "굽 변경");
                                put("isNecessary", false);
                                put("options", new JSONArray() {
                                    {
                                        put(new JSONObject() {
                                            {
                                                put("name", "3cm");
                                                put("price", 1000);
                                            }
                                        });
                                        put(new JSONObject() {
                                            {
                                                put("name", "5cm");
                                                put("price", 0);
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
                .post("/v1/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request.toString()));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.document("postItem", "상품 생성", this.tag));
    }

    @Test
    void testGetItem() throws Exception {
        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .get("/v1/item/{itemId}", this.itemId));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.document("getItem", "특정 상품 조회", this.tag));
    }

    @Test
    void testGetItemAll() throws Exception {
        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .get("/v1/item"));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.document("getItemAll", "모든 상품 조회", this.tag));
    }
}
