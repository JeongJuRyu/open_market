package com.tmax.cm.superstore.integrationtest;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.tmax.cm.superstore.EasyRestDocumentation;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@ExtendWith(RestDocumentationExtension.class)
@ActiveProfiles("develop-integration-test")
public class ItemControllerIntegrationTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    private String tag = "Item";

    private UUID itemId;

    @BeforeEach
    public void setUp(RestDocumentationContextProvider restDocumentation) throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .apply(MockMvcRestDocumentation.documentationConfiguration(restDocumentation))
                .build();

        {
            JSONObject createItemRequest = new JSONObject() {
                {
                    put("shopName", "서머슈슈즈");
                    put("name", "로토 스르르트 썸머 슈즈");
                    put("price", 82000);
                    put("possibleSendType", new JSONArray() {
                        {
                            put("shipping");
                        }
                    });
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
                                                }
                                            });
                                            put(new JSONObject() {
                                                {
                                                    put("name", "화이트");
                                                    put("price", 0);
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
                                                }
                                            });
                                            put(new JSONObject() {
                                                {
                                                    put("name", "5cm");
                                                    put("price", 0);
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
            MvcResult createItemResult = this.mvc.perform(RestDocumentationRequestBuilders
                    .post("/v1/item")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(createItemRequest.toString()))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();

            JSONObject createItemResponse = new JSONObject(
                    createItemResult.getResponse().getContentAsString());
            this.itemId = UUID.fromString(createItemResponse.getJSONObject("data").getString("itemId"));
        }

        {

        }
    }

    @Test
    void testPostItem() throws Exception {
        // given
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
                                            }
                                        });
                                        put(new JSONObject() {
                                            {
                                                put("name", "화이트");
                                                put("price", 0);
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
                                            }
                                        });
                                        put(new JSONObject() {
                                            {
                                                put("name", "5cm");
                                                put("price", 0);
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
                .post("/v1/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request.toString()));

        // then
        result.andDo(print())
                .andExpect(status().isOk())
                .andDo(EasyRestDocumentation.document("postItem", "상품 생성", this.tag));
    }

    @Test
    void testGetItem() throws Exception {
        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .get("/v1/item/{itemId}", this.itemId));

        // then
        result.andDo(print())
                .andExpect(status().isOk())
                .andDo(EasyRestDocumentation.document("getItem", "특정 상품 조회", this.tag));
    }

    @Test
    void testGetItemAll() throws Exception {
        // when
        ResultActions result = this.mvc.perform(RestDocumentationRequestBuilders
                .get("/v1/item"));

        // then
        result.andDo(print())
                .andExpect(status().isOk())
                .andDo(EasyRestDocumentation.document("getItemAll", "모든 상품 조회", this.tag));
    }

    @Test
    void image_test() throws Exception{
        MockMultipartFile file = new MockMultipartFile("image",
                "ddx.png",
                "image/png",
                new FileInputStream("C:\\Users\\jaehoon_choi3\\Pictures\\Screenshots\\ddx.png"));

        List<MultipartFile> imageFiles = List.of(
                new MockMultipartFile("test1", "test1.PNG", MediaType.IMAGE_PNG_VALUE, "test1".getBytes()),
                new MockMultipartFile("test2", "test2.PNG", MediaType.IMAGE_PNG_VALUE, "test2".getBytes())
        );

        this.mvc.perform(
                        multipart("/v1/item/images")
                                .file("images", imageFiles.get(0).getBytes())
                                .contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
}
