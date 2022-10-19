package com.tmax.cm.superstore.integrationtest.mypage;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import com.tmax.cm.superstore.EasyRestDocumentation;
import com.tmax.cm.superstore.integrationtest.AbstractIntegrationTest;

@SpringBootTest
@Transactional
@ExtendWith(RestDocumentationExtension.class)
@ActiveProfiles("develop-integration-test")
public class ReviewIntegrationTest extends AbstractIntegrationTest {

	private String tag = "Review";

	@Test
	void testPostReview() throws Exception {
		//given
		JSONObject request = new JSONObject(){
			{
				put("content", "너무 맛있는데요..?");
				put("starRating", 4.5);
				put("orderItemId", "067ad728-1e0b-4ed3-b560-e0ae58ccaa59");
			}
		};
		//when
		ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
			.post("/v1/review")
			.contentType(MediaType.APPLICATION_JSON)
			.content(request.toString())
			.header(HttpHeaders.AUTHORIZATION, this.testJwtGenerator.generate()));
		//then
		result.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andDo(EasyRestDocumentation.documentWithJwt("postReview", "리뷰 작성하기", this.tag));
	}


}
