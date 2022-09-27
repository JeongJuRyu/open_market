package com.tmax.cm.superstore.item.service;

import com.tmax.cm.superstore.code.ResponseCode;
import com.tmax.cm.superstore.error.exception.InternalServerErrorException;
import com.tmax.cm.superstore.item.dto.FileInfo;
import com.tmax.cm.superstore.item.dto.MediaResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final String uploadPath = "http://192.168.159.42:8888/images/upload";
    private final String uploadManyPath = "http://192.168.159.42:8888/images/upload_many";
    private final RestTemplate restTemplate;

    public List<FileInfo> uploadImages(List<MultipartFile> images) {
        return requestUpload(images);
    }

    public List<FileInfo> requestUpload(List<MultipartFile> images) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

        for (MultipartFile attachedImage : images) {
            body.add("file", attachedImage.getResource());
        }
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        if (images.size() == 1) {
            ResponseEntity<MediaResponse.Single> response = restTemplate.postForEntity(uploadPath,
                    requestEntity, MediaResponse.Single.class);
            if (response.getStatusCode() != HttpStatus.OK || response.getBody().getCode() != HttpStatus.OK.value()) {
                throw new InternalServerErrorException(ResponseCode.ERROR_INTERNAL_SERVER_ERROR);
            }
            return Collections.singletonList(response.getBody().getData());
        } else {
            ResponseEntity<MediaResponse.Multi> response = restTemplate.postForEntity(
                    uploadManyPath, requestEntity, MediaResponse.Multi.class);
            if (response.getStatusCode() != HttpStatus.OK || response.getBody().getCode() != HttpStatus.OK.value()) {
                throw new InternalServerErrorException(ResponseCode.ERROR_INTERNAL_SERVER_ERROR);
            }
            return response.getBody().getData();
        }
    }
}
