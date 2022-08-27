package com.tmax.cm.superstore.item.service;

import com.tmax.cm.superstore.item.dto.MediaResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final String baseUrl = "http://192.168.159.42:8888/images/upload";
    private final RestTemplate restTemplate;
    public HttpStatus uploadImages(MultipartFile images) throws IOException {
        return requestUpload(images);
    }
    public HttpStatus requestUpload(MultipartFile images) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("file", images.getResource());
        ResponseEntity<MediaResponse.Single> response = restTemplate.postForEntity(baseUrl, requestEntity, MediaResponse.Single.class);
        System.out.println(response.getStatusCode());
        return response.getStatusCode();

        /*WebClient webClient = WebClient.create();
        URI url = UriComponentsBuilder.fromHttpUrl(baseUrl).build().toUri();
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("screenshot", images.getResource());
        Mono<HttpStatus> httpStatusMono = webClient.post()
            .uri(url)
            .contentType(MediaType.MULTIPART_FORM_DATA)
            .accept(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromMultipartData(builder.build()))
            .exchangeToMono(response -> {
                System.out.println(response.statusCode());
                return null;
                    return response.bodyToMono(HttpStatus.class).thenReturn(response.statusCode());
                } else {
                    throw new ServiceException("Error uploading file");
            });
        WebClient client = WebClient.create(baseUrl);
        byte[] fileContent = Files.readAllBytes(images.toPath());
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("name", "david");
        builder.part("version", "1.1.0");
        builder.part("uploadfile", new ByteArrayResource(fileContent))
            .header("Content-Disposition",
                "form-data; name= uploadfile; filename=1234.jpg");

        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("file", images.getBytes());

            Mono<FileInfo> response = client.post()
                    .uri("images/upload")
                    .header(HttpHeaders.CONTENT_TYPE, String.valueOf(MediaType.MULTIPART_FORM_DATA))
                    .body(BodyInserters.fromMultipartData(multiValueMap))
                    .retrieve()
                    .bodyToMono(FileInfo.class);
        return httpStatusMono;*/
    }
}
