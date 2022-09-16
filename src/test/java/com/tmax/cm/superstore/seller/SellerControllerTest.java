package com.tmax.cm.superstore.seller;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.Schema;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.common.ResponseDto;
import com.tmax.cm.superstore.common.config.security.SecurityConfig;
import com.tmax.cm.superstore.seller.controller.SellerController;
import com.tmax.cm.superstore.seller.dto.CreateSellerDto;
import com.tmax.cm.superstore.seller.entity.Seller;
import com.tmax.cm.superstore.seller.service.SellerService;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.UUID;

import static com.tmax.cm.superstore.ApiDocumentUtils.getDocumentRequest;
import static com.tmax.cm.superstore.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureRestDocs
@ExtendWith({RestDocumentationExtension.class})
@WebMvcTest(value = SellerController.class, excludeFilters = {
	@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
})
public class SellerControllerTest {

	private MockMvc mvc;

//	@Autowired
//	private ObjectMapper objectMapper;

	@Autowired
	@MockBean
	private SellerService sellerService;

	private CreateSellerDto.Request createSellerDto;
	private Seller seller;

	@BeforeEach
	void init(RestDocumentationContextProvider restDocumentation) throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(new SellerController(sellerService))
			.addFilters(new CharacterEncodingFilter("UTF-8", true))
			.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
			.apply(MockMvcRestDocumentation.documentationConfiguration(restDocumentation))
			.build();

		createSellerDto = CreateSellerDto.Request.builder()
			.loginId("로그인 아이디")
			.password("password")
			.sellerName("판매자 이름")
			.sellerEmail("이메일@tmax.co.kr")
			.sellerPhoneNum("010-1234-5678")
			.build();

		seller = Seller.builder(createSellerDto)
			.sellerId(UUID.randomUUID())
			.isDeleted(false)
			.build();
	}

	@Test
	@DisplayName("createSeller 테스트입니다.")
	void createSeller() throws Exception {
		ResponseDto<CreateSellerDto.Response> createSellerResponseDto =
			ResponseDto.<CreateSellerDto.Response>builder()
				.responseCode(ResponseCode.SELLER_CREATE)
				.data(CreateSellerDto.Response.builder(seller).build())
				.build();

		JSONObject request = new JSONObject(){
			{
				put("loginId", "로그인아이디");
				put("password", "비밀번호");
				put("sellerName", "판매자 이름");
				put("sellerEmail", "이메일@tmax.co.kr");
				put("sellerPhoneNum", "010-1234-5678");
			}
		};

		given(sellerService.createSeller(any())).willReturn(createSellerResponseDto);
		mvc.perform(RestDocumentationRequestBuilders.post("/v1/seller/create")
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8")
				.content(request.toString()))
			.andDo(MockMvcRestDocumentationWrapper.document("creaateSeller",
				MockMvcRestDocumentationWrapper.resourceDetails()
					.tag("Seller")
					.description("판매자 계정 회원가입")
					.requestSchema(new Schema("CreateSellerRequest"))
					.responseSchema(new Schema("CreateSellerResponse")),
				getDocumentRequest(), getDocumentResponse(),
				requestFields(
					fieldWithPath("loginId").type(JsonFieldType.STRING).description("로그인 아이디"),
					fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호"),
					fieldWithPath("sellerName").type(JsonFieldType.STRING).description("판매자 이름"),
					fieldWithPath("sellerEmail").type(JsonFieldType.STRING).description("판매자 이메일"),
					fieldWithPath("sellerPhoneNum").type(JsonFieldType.STRING).description("판매자 전화번호")
				),
				responseFields(
					fieldWithPath("status").description("API 호출 성공 상태"),
					fieldWithPath("code").description("SuperStore 성공 코드"),
					fieldWithPath("message").description("응답 메세지"),
					fieldWithPath("data.sellerId").type(JsonFieldType.STRING).description("판매자 고유 ID"),
					fieldWithPath("data.loginId").type(JsonFieldType.STRING).description("로그인 아이디"),
					fieldWithPath("data.password").type(JsonFieldType.STRING).description("비밀번호"),
					fieldWithPath("data.sellerName").type(JsonFieldType.STRING).description("판매자 이름"),
					fieldWithPath("data.sellerEmail").type(JsonFieldType.STRING).description("판매자 이메일"),
					fieldWithPath("data.sellerPhoneNum").type(JsonFieldType.STRING).description("판매자 전화번호"),
					fieldWithPath("data.isDeleted").type(JsonFieldType.BOOLEAN).description("삭제 여부")
				)
			))
			.andExpect(status().isOk());

	}
}
