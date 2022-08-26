package com.tmax.cm.superstore.item.service;

import com.tmax.cm.superstore.item.dto.FileInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final String baseUrl = "http://192.168.159.42:8888/";

    public Mono<FileInfo> uploadImages(MultipartFile images) throws IOException {

        return requestUpload(images);
    }

    public Mono<FileInfo> requestUpload(MultipartFile images) throws IOException {
        WebClient client = WebClient.create(baseUrl);

        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();

        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("file", images.getBytes());

            Mono<FileInfo> response = client.post()
                    .uri("images/upload")
                    .header(HttpHeaders.CONTENT_TYPE, String.valueOf(MediaType.MULTIPART_FORM_DATA))
                    .body(BodyInserters.fromMultipartData(multiValueMap))
                    .retrieve()
                    .bodyToMono(FileInfo.class);

        return response;
    }
}
