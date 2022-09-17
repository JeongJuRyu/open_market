// package com.tmax.cm.superstore.integrationtest.user;
//
// import org.json.JSONException;
// import org.json.JSONObject;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.MediaType;
// import org.springframework.restdocs.RestDocumentationContextProvider;
// import org.springframework.restdocs.RestDocumentationExtension;
// import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
// import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
// import org.springframework.test.context.ActiveProfiles;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.ResultActions;
// import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
// import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;
// import org.springframework.transaction.annotation.Transactional;
// import org.springframework.web.context.WebApplicationContext;
// import org.springframework.web.filter.CharacterEncodingFilter;
//
// import com.tmax.cm.superstore.EasyRestDocumentation;
//
// @SpringBootTest
// @Transactional
// @ExtendWith(RestDocumentationExtension.class)
// @ActiveProfiles("develop-integration-test")
// public class UserIntegrationTest {
// 	@Autowired
// 	private WebApplicationContext context;
//
// 	private MockMvc mvc;
//
// 	private String tag = "Item";
//
// 	@BeforeEach
// 	public void setUp(RestDocumentationContextProvider restDocumentation) throws Exception {
// 		this.mvc = MockMvcBuilders.webAppContextSetup(context)
// 			.addFilter(new CharacterEncodingFilter("UTF-8", true))
// 			.apply(MockMvcRestDocumentation.documentationConfiguration(restDocumentation))
// 			.build();
// 	}
//
// 	@Test
// 	void 유저_회원가입() throws Exception {
// 		//given
// 		JSONObject jsonObject = new JSONObject() {
// 			{
// 				put("email", "totw2018@naver.com");
// 				put("userPhoneNum", "010-4523-6994");
// 				put("password", "1234");
// 				put("address", "사랑시 행복구 엄복동");
// 			}
// 		};
// 		//when
// 		ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
// 			.post("/v1/users")
// 			.contentType(MediaType.APPLICATION_JSON)
// 			.content(jsonObject.toString()));
//
// 		//then
// 		result.andDo(MockMvcResultHandlers.print())
// 			.andExpect(MockMvcResultMatchers.status().isOk())
// 			.andDo(EasyRestDocumentation.document("createUser", "회원가입", this.tag));
// 	}
//
// 	@Test
// 	void 유저_로그인_성공() throws Exception {
// 		JSONObject jsonObject = new JSONObject(){
// 			{
// 				put("email", "totw2018@naver.com");
// 				put("password", "1234");
// 			}
// 		};
// 		ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
// 			.post("/v1/login")
// 			.contentType(MediaType.APPLICATION_JSON)
// 			.content(jsonObject.toString()));
//
// 		result.andDo(MockMvcResultHandlers.print())
// 			.andExpect(MockMvcResultMatchers.status().isOk())
// 			.andExpect(MockMvcResultMatchers.jsonPath("$.accesstoken").exists())
// 			.andExpect(MockMvcResultMatchers.jsonPath("$.refreshToken").exists())
// 			.andDo(EasyRestDocumentation.document("Login", "로그인", this.tag));
// 	}
//
// 	@Test
// 	void 유저_로그인_존재하지_않는_아이디() throws Exception{
// 		JSONObject jsonObject = new JSONObject(){
// 			{
// 				put("email", "totw2018@naver.com");
// 				put("password", "1234");
// 			}
// 		};
// 		ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
// 			.post("/v1/login")
// 			.contentType(MediaType.APPLICATION_JSON)
// 			.content(jsonObject.toString()));
//
// 		//result.andDo(MockMvcResultHandlers.print())
// 		//	.andExpect(MockMvcResultMatchers.status().)
// 	}
//
// 	@Test
// 	void 유저_로그인_비밀번호_불일치(){
//
// 	}
// }
