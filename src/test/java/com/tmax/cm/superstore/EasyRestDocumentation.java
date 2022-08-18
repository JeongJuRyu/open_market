package com.tmax.cm.superstore;

import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;

import com.epages.restdocs.apispec.ResourceDocumentation;
import com.epages.restdocs.apispec.ResourceSnippetParameters;

public abstract class EasyRestDocumentation {

    private EasyRestDocumentation() {
    }

    public static RestDocumentationResultHandler document(String operationId, String description, String tag) {
        ResourceSnippetParameters parameter = ResourceSnippetParameters.builder()
                .tag(tag)
                .description(description)
                .build();

        return MockMvcRestDocumentation.document(operationId, ResourceDocumentation.resource(parameter));
    }

}
