package com.tmax.cm.superstore.item.dto;

import com.tmax.cm.superstore.item.entity.ItemImage;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemImageInfo {
    private final String fileId;

    @Builder
    public ItemImageInfo(ItemImage itemImage){
        this.fileId = itemImage.getFileId();
    }
}
