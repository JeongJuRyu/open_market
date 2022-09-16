package com.tmax.cm.superstore.item.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ImageResponseDto {
    private int code;
    private String message;
    private FileInfo data;
}
