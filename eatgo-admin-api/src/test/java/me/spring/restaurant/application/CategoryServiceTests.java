package me.spring.restaurant.application;

import me.spring.restaurant.domain.Category;
import me.spring.restaurant.domain.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class CategoryServiceTests {

    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    public void getRegions() {
        List<Category> mockCategory = new ArrayList<>();
        mockCategory.add(Category.builder().name("Korean Food").build());

        given(categoryRepository.findAll()).willReturn(mockCategory);

        List<Category> categories = categoryService.getCategories();

        Category category = categories.get(0);

        assertThat(category.getName(), is("Korean Food"));
    }

    @Test
    public void addCategory() {
        Category category = Category.builder().name("Korean Food").build();

        given(categoryRepository.save(any())).willReturn(category);

        Category created = categoryService.addCategory(category);

        verify(categoryRepository).save(any());

        assertThat(created.getName(), is("Korean Food"));
    }
}
