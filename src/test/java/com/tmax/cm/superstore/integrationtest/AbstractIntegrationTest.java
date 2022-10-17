package com.tmax.cm.superstore.integrationtest;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.tmax.cm.superstore.integrationtest.util.TestJwtGenerator;

@Transactional
@SpringBootTest
@ExtendWith(RestDocumentationExtension.class)
@ActiveProfiles("develop-integration-test")
// @ContextConfiguration(classes = {
// IntegrationTestDataLoader.class
// })
public abstract class AbstractIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    protected MockMvc mvc;

    @Value("${jwt.secret}")
    protected String secretKey;

    protected TestJwtGenerator testJwtGenerator;

    // @Autowired
    // private IntegrationTestData testData;

    @BeforeEach
    public void setUp(RestDocumentationContextProvider restDocumentation) throws Exception {

        this.mvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .apply(MockMvcRestDocumentation.documentationConfiguration(restDocumentation))
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();

        this.testJwtGenerator = new TestJwtGenerator(this.secretKey);
    }
}
