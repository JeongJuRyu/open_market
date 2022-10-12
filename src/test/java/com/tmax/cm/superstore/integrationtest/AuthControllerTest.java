package com.tmax.cm.superstore.integrationtest;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
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
public class AuthControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    private String tag = "Auth";

    @BeforeEach
    public void setUp(RestDocumentationContextProvider restDocumentation) throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .apply(MockMvcRestDocumentation.documentationConfiguration(restDocumentation))
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    @WithUserDetails("totw2018@naver.com")
    void testTest() throws Exception {
        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .get("/v1/auth/test"));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.document("test", "인증 테스트", this.tag));
    }

    @Test
    void testTestFailedWithUnauthorized() throws Exception {
        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .get("/v1/auth/test"));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(SecurityMockMvcResultMatchers.unauthenticated())
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andDo(EasyRestDocumentation.document("testFailedWithUnauthorized", "인증 테스트", this.tag));
    }

    @Test
    void testTest1() throws Exception {
        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .get("/v1/auth/test1"));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
