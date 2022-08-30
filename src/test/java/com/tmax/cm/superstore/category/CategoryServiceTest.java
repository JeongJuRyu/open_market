package com.tmax.cm.superstore.category;

import com.tmax.cm.superstore.category.entity.Category;
import com.tmax.cm.superstore.category.entity.dto.CategoryDto;
import com.tmax.cm.superstore.category.repository.CategoryRepository;
import com.tmax.cm.superstore.category.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    public void create_category() throws Exception {
        //given
        List<Category> categories = createCategory();
        given(categoryRepository.findAll())
                .willReturn(categories);

        //when
        CategoryDto categoryRoot = categoryService.getCategory(0L);

        //then
        verify(categoryRepository, atLeastOnce()).findAll();

        //root
        assertEquals(categoryRoot.getSubCategories().size(), 2);
        //sub1
        assertEquals(categoryRoot.getSubCategories().get(0).getSubCategories().size(), 2);
        //sub2
        assertEquals(categoryRoot.getSubCategories().get(1).getSubCategories().size(), 2);


    }

    private List<Category> createCategory(){
        Category sub1 = new Category("SUB1", 0l);
        Category sub2 = new Category("SUB2", 0l);
        Category sub11 = new Category("SUB1-1", 1l);
        Category sub12 = new Category("SUB1-2", 1l);
        Category sub21 = new Category("SUB2-1", 2l);
        Category sub22 = new Category("SUB2-2", 2l);
        ReflectionTestUtils.setField(sub1, "categoryId", 1l);
        ReflectionTestUtils.setField(sub2, "categoryId", 2l);
        ReflectionTestUtils.setField(sub11, "categoryId", 3l);
        ReflectionTestUtils.setField(sub12, "categoryId", 4l);
        ReflectionTestUtils.setField(sub21, "categoryId", 5l);
        ReflectionTestUtils.setField(sub22, "categoryId", 6l);

        return List.of(
                sub1, sub2, sub11, sub12, sub21, sub22
        );
    }

}
