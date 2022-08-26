package com.tmax.cm.superstore.item.entity;

import com.tmax.cm.superstore.item.dto.FileInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_image_id")
    private long itemImageId;

    private String fileId;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private ItemImage(FileInfo fileInfo){
        this.fileId = fileInfo.getFileId();
    }
}
