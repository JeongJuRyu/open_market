package com.tmax.cm.superstore.category.service;

import static java.util.stream.Collectors.groupingBy;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tmax.cm.superstore.category.entity.Category;
import com.tmax.cm.superstore.category.entity.dto.CategoryDto;
import com.tmax.cm.superstore.category.entity.dto.mapper.CategoryMapper;
import com.tmax.cm.superstore.category.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryDto getCategory(Long categoryId){
        Map<Long, List<CategoryDto>> groupingByParent = categoryRepository.findAll()
                .stream()
                .map(ce -> new CategoryDto(ce.getId(), ce.getName(), ce.getParentId()))
                .collect(groupingBy(CategoryDto::getParentId));

        CategoryDto category;

        if (categoryId == 0){
            category = new CategoryDto(0L, "ROOT", null);
        } else{
            category = categoryMapper.categoryToCategoryDto(categoryRepository.findById(categoryId).orElseThrow(IllegalArgumentException::new));
        }
        addSubCategories(category, groupingByParent);

        return category;
    }

    public Category getCategoryEntity(Long categoryId) {
        return this.categoryRepository.findById(categoryId).orElseThrow(IllegalArgumentException::new);
    }

    private void addSubCategories(CategoryDto parent, Map<Long, List<CategoryDto>> groupingByParentId){
        List<CategoryDto> subCategories = groupingByParentId.get(parent.getCategoryId());

        if (subCategories == null)
            return;
        parent.setSubCategories(subCategories);
        subCategories
                .forEach(s -> {
                    addSubCategories(s, groupingByParentId);
                });
    }


}
