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
public class ReservationControllerIntegrationTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    private String tag = "Reservation";

    @BeforeEach
    public void setUp(RestDocumentationContextProvider restDocumentation) throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .apply(MockMvcRestDocumentation.documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    void testGetReservableDates() throws Exception {
        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .get("/v1/reservation/year/{year}/month/{month}", 2022, 8));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.document("getReservableDates", "예약가능한 날짜 조회", this.tag));
    }

    @Test
    void testGetReservableTimes() throws Exception {
        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .get("/v1/reservation/date/{date}/time", "2022-08-24"));

        // then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(EasyRestDocumentation.document("getReservableTimes", "예약가능한 시간대 조회", this.tag));

    }
}
