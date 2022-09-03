// package com.tmax.cm.superstore.integrationtest.mypage;
//
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.restdocs.RestDocumentationContextProvider;
// import org.springframework.restdocs.RestDocumentationExtension;
// import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
// import org.springframework.security.web.access.channel.ChannelProcessingFilter;
// import org.springframework.test.context.ActiveProfiles;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.MockMvcBuilder;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;
// import org.springframework.transaction.annotation.Transactional;
// import org.springframework.web.context.WebApplicationContext;
// import org.springframework.web.filter.CharacterEncodingFilter;
//
// @SpringBootTest
// @Transactional
// @ExtendWith(RestDocumentationExtension.class)
// @ActiveProfiles("develop-integration-test")
// public class ReviewIntegrationTest {
// 	@Autowired
// 	private WebApplicationContext context;
//
// 	private MockMvc mvc;
//
// 	@BeforeEach
// 	public void setUp(RestDocumentationContextProvider restDocumentation){
// 		this.mvc = MockMvcBuilders.webAppContextSetup(context)
// 			.addFilter(new CharacterEncodingFilter("UTF-8", true))
// 			.apply(MockMvcRestDocumentation.documentationConfiguration(restDocumentation))
// 			.build();
// 	}
//
// }
