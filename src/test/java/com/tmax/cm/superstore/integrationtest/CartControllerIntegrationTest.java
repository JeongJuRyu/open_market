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

public class CartControllerIntegrationTest extends AbstractIntegrationTest {

    private String tag = "Cart";

    @Test
    void testPostCartItem() throws Exception {
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
                .post("/v1/cart/cartItem")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request.toString())
                .header(HttpHeaders.AUTHORIZATION, this.testJwtGenerator.generate()));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.documentWithJwt("postCartItem", "?????? ?????? ??????", this.tag));
    }

    @Test
    void testPostCartReservationItem() throws Exception {
        // given
        JSONObject request = new JSONObject() {
            {
                put("itemId", "82cdc2eb-5ee8-4bde-8e32-654054b7fc16");
                put("reservationDate", "2022-08-08T20:00:00");
                put("dayOfWeek", "MONDAY");
                put("reservationHeadcount", 36);
                put("guestName", "?????????");
                put("guestPhoneNumber", "010-1234-5678");
                put("guestEmail", "abc_12@tmax.co.kr");
                put("reservationRequirement", "50?????? ?????? 36?????? ???????????? ~");
                put("selectedOptions", new JSONArray() {
                    {
                        put(new JSONObject() {
                            {
                                put("selectedOptionCount", 1);
                                put("cartOptionGroups", new JSONArray() {
                                    {
                                        put(new JSONObject() {
                                            {
                                                put("optionGroupId", "8b41baa5-6118-4949-886f-abe34ca69cfe");
                                                put("cartOptions", new JSONArray() {
                                                    {
                                                        put(new JSONObject() {
                                                            {
                                                                put("optionId", "08a1f664-c17c-4ff3-bb26-6519b7b2bff1");
                                                                put("cartItemOptionCount", 1);
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
                .post("/v1/cart/cartReservationItem")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request.toString())
                .header(HttpHeaders.AUTHORIZATION, this.testJwtGenerator.generate()));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(EasyRestDocumentation.documentWithJwt("postCartReservationItem", "?????? ?????? ??????", this.tag));
    }

    @Test
    void testGetCart() throws Exception {
        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .get("/v1/cart")
                .header(HttpHeaders.AUTHORIZATION, this.testJwtGenerator.generate()));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.documentWithJwt("getCart", "?????? ??????", this.tag));
    }

    @Test
    void testGetCartItem() throws Exception {
        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .get("/v1/cart/cartItem/{cartItemId}", "024c768f-f693-4c2a-a7a1-70830f5c2114")
                .header(HttpHeaders.AUTHORIZATION, this.testJwtGenerator.generate()));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.documentWithJwt("getCartItem", "?????? ?????? ??????", this.tag));
    }

    @Test
    void testGetCartReservationItem() throws Exception {
        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .get("/v1/cart/cartReservationItem/{cartItemId}", "0ceda629-b012-45c4-bdf9-6e5787ba4e62")
                .header(HttpHeaders.AUTHORIZATION, this.testJwtGenerator.generate()));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(EasyRestDocumentation.documentWithJwt("getCartReservationItem", "?????? ?????? ??????", this.tag));
    }

    @Test
    void testDeleteCartItems() throws Exception {
        // given
        JSONObject request = new JSONObject() {
            {
                put("cartItemIds", new JSONArray() {
                    {
                        put("024c768f-f693-4c2a-a7a1-70830f5c2114");
                    }
                });
            }
        };

        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .delete("/v1/cart/cartItem")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request.toString())
                .header(HttpHeaders.AUTHORIZATION, this.testJwtGenerator.generate()));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.documentWithJwt("deleteCartItem", "?????? ?????? ?????? ??????", this.tag));
    }

    @Test
    void testPutCartItem() throws Exception {
        // given
        JSONObject request = new JSONObject() {
            {
                put("sendType", "visit");
                put("selectedOptions", new JSONArray() {
                    {
                        put(new JSONObject() {
                            {
                                put("selectedOptionCount", 1);
                                put("cartOptionGroups", new JSONArray() {
                                    {
                                        put(new JSONObject() {
                                            {
                                                put("optionGroupId", "62cfa7ab-26f5-46cf-af80-f9dedfda5693");
                                                put("cartOptions", new JSONArray() {
                                                    {
                                                        put(new JSONObject() {
                                                            {
                                                                put("optionId", "ca24924e-2007-4177-8e5a-a55225c1b2d6");
                                                                put("cartItemOptionCount", 1);
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
                .put("/v1/cart/cartItem/{cartItemId}", "024c768f-f693-4c2a-a7a1-70830f5c2114")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request.toString())
                .header(HttpHeaders.AUTHORIZATION, this.testJwtGenerator.generate()));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.documentWithJwt("putCartItem", "?????? ?????? ??????", this.tag));
    }

    @Test
    void testPutCartReservationItem() throws Exception {
        // given
        JSONObject request = new JSONObject() {
            {
                put("reservationDate", "2022-08-08T20:00:00");
                put("dayOfWeek", "MONDAY");
                put("reservationHeadcount", 36);
                put("guestName", "?????????");
                put("guestPhoneNumber", "010-1234-5678");
                put("guestEmail", "abc_12@tmax.co.kr");
                put("reservationRequirement", "50?????? ?????? 36?????? ???????????? ~");
                put("selectedOptions", new JSONArray() {
                    {
                        put(new JSONObject() {
                            {
                                put("selectedOptionCount", 1);
                                put("cartOptionGroups", new JSONArray() {
                                    {
                                        put(new JSONObject() {
                                            {
                                                put("optionGroupId", "8b41baa5-6118-4949-886f-abe34ca69cfe");
                                                put("cartOptions", new JSONArray() {
                                                    {
                                                        put(new JSONObject() {
                                                            {
                                                                put("optionId", "75bd49ea-63e4-4641-a58d-4c24e481a3df");
                                                                put("cartItemOptionCount", 1);
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
                .put("/v1/cart/cartReservationItem/{cartItemId}", "0ceda629-b012-45c4-bdf9-6e5787ba4e62")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request.toString())
                .header(HttpHeaders.AUTHORIZATION, this.testJwtGenerator.generate()));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(EasyRestDocumentation.documentWithJwt("putCartReservationItem", "?????? ?????? ??????", this.tag));
    }
}
