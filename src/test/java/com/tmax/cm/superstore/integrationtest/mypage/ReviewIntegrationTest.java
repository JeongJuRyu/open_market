package com.tmax.cm.superstore.integrationtest.mypage;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@ExtendWith(RestDocumentationExtension.class)
public class ReviewIntegrationTest {
}
