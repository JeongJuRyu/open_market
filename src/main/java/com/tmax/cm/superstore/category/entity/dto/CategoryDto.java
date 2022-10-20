package com.tmax.cm.superstore.category.entity.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryDto {

    private Long categoryId;
    private String categoryName;
    private Long parentId;
    private List<CategoryDto> subCategories;

    public CategoryDto(Long categoryId, String categoryName, Long parentId) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.parentId = parentId;
    }
}
