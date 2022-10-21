package com.tmax.cm.superstore;

import org.springframework.http.HttpHeaders;
import org.springframework.restdocs.headers.HeaderDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;

import com.epages.restdocs.apispec.ParameterDescriptorWithType;
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

    public static RestDocumentationResultHandler documentWithRequestParameter(String operationId, String description,
            String tag, ParameterDescriptorWithType... requestParameter) {

        ResourceSnippetParameters parameter = ResourceSnippetParameters.builder()
                .tag(tag)
                .description(description)
                .requestParameters(requestParameter)
                .build();
        return MockMvcRestDocumentation.document(operationId, ResourceDocumentation.resource(parameter));
    }

    public static RestDocumentationResultHandler documentWithJwt(String operationId, String description, String tag) {
        ResourceSnippetParameters parameter = ResourceSnippetParameters.builder()
                .tag(tag)
                .description(description)
                .requestHeaders(HeaderDocumentation.headerWithName(HttpHeaders.AUTHORIZATION).description("JWT"))
                .build();

        return MockMvcRestDocumentation.document(operationId, ResourceDocumentation.resource(parameter));
    }

    public static RestDocumentationResultHandler documentWithJwtAndRequestParameter(String operationId,
            String description, String tag, ParameterDescriptorWithType... requestParameter) {

        ResourceSnippetParameters parameter = ResourceSnippetParameters.builder()
                .tag(tag)
                .description(description)
                .requestHeaders(HeaderDocumentation.headerWithName(HttpHeaders.AUTHORIZATION).description("JWT"))
                .requestParameters(requestParameter)
                .build();

        return MockMvcRestDocumentation.document(operationId, ResourceDocumentation.resource(parameter));
    }

    public static ParameterDescriptorWithType createRequestParameter(String name, String description,
            boolean isRequired) {

        ParameterDescriptorWithType requestParameter = ResourceDocumentation.parameterWithName(name);
        requestParameter.description(description);
        if (!isRequired) {
            requestParameter.optional();
        }

        return requestParameter;
    }
}
