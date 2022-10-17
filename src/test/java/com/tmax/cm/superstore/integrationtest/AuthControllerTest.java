package com.tmax.cm.superstore.integrationtest;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.tmax.cm.superstore.EasyRestDocumentation;

public class AuthControllerTest extends AbstractIntegrationTest{

    private String tag = "Auth";

    @Test
    void testTest() throws Exception {
        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .get("/v1/auth/test")
                .header(HttpHeaders.AUTHORIZATION, this.testJwtGenerator.generate("totw2018@naver.com")));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                // NOTE jwt 토큰 인증을 swagger에 반영하려면 document() 대신 documentWithJwt() 메소드 사용
                .andDo(EasyRestDocumentation.documentWithJwt("test", "인증 테스트", this.tag));
    }

    @Test
    void testTestFailedWithNoJwt() throws Exception {
        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .get("/v1/auth/test"));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andDo(EasyRestDocumentation.document("testFailedWithNoJwt", "인증 테스트", this.tag));
    }

    @Test
    void testTestFailedWithExpiredJwt() throws Exception {
        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .get("/v1/auth/test")
                .header(HttpHeaders.AUTHORIZATION, this.testJwtGenerator.generateExpiredToken("totw2018@naver.com")));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andDo(EasyRestDocumentation.documentWithJwt("testFailedWithExpiredJwt", "인증 테스트", this.tag));
    }

    @Test
    void testTestFailedWithNotFoundEmail() throws Exception {
        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .get("/v1/auth/test")
                .header(HttpHeaders.AUTHORIZATION, this.testJwtGenerator.generate("asdf@naver.com")));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andDo(EasyRestDocumentation.documentWithJwt("testFailedWithNotFoundEmail", "인증 테스트", this.tag));
    }

    @Test
    void testLogin() throws Exception {
        // given
        JSONObject request = new JSONObject() {
            {
                put("email", "totw2018@naver.com");
                put("password", "1234");
            }
        };

        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .post("/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request.toString()));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.document("login", "로그인", this.tag));
    }

    @Test
    void testLoginFailedWithWrongPassword() throws Exception {
        // given
        JSONObject request = new JSONObject() {
            {
                put("email", "totw2018@naver.com");
                put("password", "asdf");
            }
        };

        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .post("/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request.toString()));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andDo(EasyRestDocumentation.document("loginFailedWithWrongPassword", "로그인", this.tag));
    }

    @Test
    void testLoginFailedWithWrongEmail() throws Exception {
        // given
        JSONObject request = new JSONObject() {
            {
                put("email", "asdf@naver.com");
                put("password", "1234");
            }
        };

        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .post("/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request.toString()));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andDo(EasyRestDocumentation.document("loginFailedWithWrongEmail", "로그인", this.tag));
    }
}
