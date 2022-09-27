package com.tmax.cm.superstore.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo {
    @JsonProperty("file_name")
    private String fileName;

    @JsonProperty("file_id")
    private String fileId;
}
