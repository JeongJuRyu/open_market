package com.tmax.cm.superstore.wishlist.controller;

import com.tmax.cm.superstore.EasyRestDocumentation;
import com.tmax.cm.superstore.integrationtest.AbstractIntegrationTest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@ExtendWith(RestDocumentationExtension.class)
@ActiveProfiles("develop-integration-test")
class WishlistControllerTest extends AbstractIntegrationTest {

    private String tag = "Wishlist";


    @Test
    public void PostWishlistGroupCreate() throws Exception {
        JSONObject request = new JSONObject() {
            {
                put("wishlistGroupName", "test1");
            }
        };

        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .post("/v1/wishlist/wishlistGroup")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, this.testJwtGenerator.generate())
                .content(request.toString()));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.document("postWishlistGroupCreate", "찜 그룹 생성", this.tag));
    }

    @Test
    public void testGetWishlistGroupAll() throws Exception {
        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .get("/v1/wishlist/wishlistGroup")
                .header(HttpHeaders.AUTHORIZATION, this.testJwtGenerator.generate()));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.document("getWishlistGroupAll", "찜 그룹 조회", this.tag));
    }

    @Test
    public void testGetWishlistItem() throws Exception {
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .get("/v1/wishlist/wishlistItem", "")
                .param("wishlistGroupId", "1")
                .header(HttpHeaders.AUTHORIZATION, this.testJwtGenerator.generate())
        );

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.document("getWishlistItem", "찜한 상품 조회", this.tag));
    }

    @Test
    public void testGetWishlistItemAll() throws Exception {
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .get("/v1/wishlist/wishlistItem", "")
                .header(HttpHeaders.AUTHORIZATION, this.testJwtGenerator.generate())
        );

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.document("getWishlistItem", "찜한 상품 조회", this.tag));
    }

    @Test
    public void testPatchWishlistGroup() throws Exception {
        //given
        JSONObject request = new JSONObject() {
            {
                put("wishlistGroupName", "test1");
            }
        };

        //when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .patch("/v1/wishlist/wishlistGroup/{wishlistGroupId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, this.testJwtGenerator.generate())
                .content(request.toString()));

        //then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.document("patchWishlistGroup", "찜 그룹 수정", this.tag));
    }

    @Test
    public void testPatchWishlistGroupOrder() throws Exception {
        //given

        JSONObject request = new JSONObject() {
            {
                put("wishlistGroupIds", new JSONArray(Arrays.asList(1L, 2L)));
            }
        };

        //when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .patch("/v1/wishlist/wishlistGroup/listOrder")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, this.testJwtGenerator.generate())
                .content(request.toString())
        );

        //then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.document("patchWishlistGroupOrder", "찜 그룹 순서 변경", this.tag));
    }

    @Test
    public void testPatchWishlistItemMove() throws Exception {
        //given
        JSONObject request = new JSONObject() {
            {
                put("targetWishlistGroupId", 1L);
                put("wishlistItemIds", new JSONArray(Arrays.asList(3L, 4L)));
            }
        };

        //when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .patch("/v1/wishlist/wishlistItem/move")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, this.testJwtGenerator.generate())
                .content(request.toString())
        );

        //then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.document("patchWishlistITemMove", "찜한 상품 그룹 이동", this.tag));
    }
}