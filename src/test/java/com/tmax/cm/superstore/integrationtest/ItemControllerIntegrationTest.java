package com.tmax.cm.superstore.integrationtest;

import java.nio.charset.StandardCharsets;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.tmax.cm.superstore.EasyRestDocumentation;

public class ItemControllerIntegrationTest extends AbstractIntegrationTest {

    private String tag = "Item";

    @Test
    void testPostItem() throws Exception {
        // given
        String sellerId = "2d68d1d0-ed27-46d2-b858-da3f0aa2e430";

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
                put("categoryId", 8);
                put("itemState", "for_sale");
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
                                                put("description", "참을 수 없는 옵션의 유혹");
                                            }
                                        });
                                        put(new JSONObject() {
                                            {
                                                put("name", "화이트");
                                                put("price", 0);
                                                put("description", "참을 수 없는 옵션의 유혹");
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
                                                put("description", "참을 수 없는 옵션의 유혹");
                                            }
                                        });
                                        put(new JSONObject() {
                                            {
                                                put("name", "5cm");
                                                put("price", 0);
                                                put("description", "참을 수 없는 옵션의 유혹");
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

        MockMultipartFile file1 = new MockMultipartFile("attachment", "image.jpeg", "image/jpeg", "1".getBytes());

        MockMultipartFile requestJson = new MockMultipartFile("request", "", "application/json",
                request.toString().getBytes(StandardCharsets.UTF_8));

        // when
        ResultActions result = this.mvc
                .perform(RestDocumentationRequestBuilders.multipart("/v1/item/seller/{sellerId}/create", sellerId)
                        .file(file1)
                        .file(requestJson));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.document("postItem", "상품 생성", this.tag));
    }

    @Test
    void testGetItem() throws Exception {
        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .get("/v1/item/{itemId}", "1523bc68-e8f7-4140-b7dd-cbfe622e068a"));

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

    @Test
    void testUpdateItem() throws Exception {
        // given
        JSONObject jsonRequest = new JSONObject() {
            {
                put("name", "로토 스르르트 윈터 슈즈");
                put("price", 52000);
                put("possibleSendType", new JSONArray() {
                    {
                        put("visit");
                    }
                });
                put("categoryId", 8);
                put("itemState", "waiting");
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
                                                put("description", "참을 수 없는 옵션의 유혹");
                                            }
                                        });
                                        put(new JSONObject() {
                                            {
                                                put("name", "레드");
                                                put("price", 1000);
                                                put("description", "참을 수 없는 옵션의 유혹");
                                            }
                                        });
                                        put(new JSONObject() {
                                            {
                                                put("name", "화이트");
                                                put("price", 0);
                                                put("description", "참을 수 없는 옵션의 유혹");
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

        MockMultipartFile file1 = new MockMultipartFile("attachment", "image.jpeg", "image/jpeg", "1".getBytes());

        MockMultipartFile requestJson = new MockMultipartFile("request", "", "application/json",
                jsonRequest.toString().getBytes(StandardCharsets.UTF_8));

        // when
        MockMultipartHttpServletRequestBuilder builders = RestDocumentationRequestBuilders
                .multipart("/v1/item/update/{itemId}", "1523bc68-e8f7-4140-b7dd-cbfe622e068a");
        builders.with(request -> {
            request.setMethod("PATCH");
            return request;
        });

        ResultActions result = this.mvc.perform(builders
                .file(file1)
                .file(requestJson));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.document("updateItem", "상품 수정", this.tag));

    }

    @Test
    void testDeleteItem() throws Exception {
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .delete("/v1/item/delete/{itemId}", "1523bc68-e8f7-4140-b7dd-cbfe622e068a"));

        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.document("deleteItem", "상품 삭제", this.tag));

    }
}
