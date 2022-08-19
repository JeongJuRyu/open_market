package com.tmax.cm.superstore.category.service;

import com.tmax.cm.superstore.category.entity.Category;
import com.tmax.cm.superstore.category.entity.dto.CategoryDto;
import com.tmax.cm.superstore.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryDto createCategoryRoot() {
        Map<Long, List<CategoryDto>> groupingByParent = categoryRepository.findAll()
                .stream()
                .map(ce -> new CategoryDto(ce.getCategoryId(), ce.getCategoryName(), ce.getParentId()))
                .collect(groupingBy(CategoryDto::getParentId));

        System.out.println(groupingByParent);
        CategoryDto rootCategoryDto = new CategoryDto(0L, "ROOT", null);
        addSubCategories(rootCategoryDto, groupingByParent);

        return rootCategoryDto;
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
