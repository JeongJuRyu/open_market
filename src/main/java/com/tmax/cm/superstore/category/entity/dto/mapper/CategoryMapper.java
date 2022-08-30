package com.tmax.cm.superstore.category.entity.dto.mapper;

import com.tmax.cm.superstore.category.entity.Category;
import com.tmax.cm.superstore.category.entity.dto.CategoryDto;
import com.tmax.cm.superstore.config.CommonMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CommonMapperConfig.class)
public interface CategoryMapper {
    @Mapping(target = "categoryId", source="category.categoryId")
    @Mapping(target = "categoryName", source = "category.categoryName")
    @Mapping(target = "parentId", source = "category.parentId")
    @Mapping(target = "subCategories", ignore = true)
    CategoryDto categoryToCategoryDto (Category category);
}
