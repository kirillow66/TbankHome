package ru.tbank.edu.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.tbank.edu.model.Category;
import ru.tbank.edu.repository.Repository;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CategoryServiceTest {

    private Repository<Category, Integer> repository;
    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        repository = mock(Repository.class);
        categoryService = new CategoryService(repository);
    }

    @Test
    public void testGetAllCategories() {
        Category category1 = new Category(1, "stable", "Stables");
        Category category2 = new Category(2, "Category2","Cat2");
        when(repository.findAll()).thenReturn(Arrays.asList(category1, category2));

        Collection<Category> categories = categoryService.getAllCategories();

        assertEquals(2, categories.size());
        verify(repository).findAll();
    }

    @Test
    public void testGetCategoryById() {
        Category category = new Category(1, "stable", "Stables");
        when(repository.findById(1)).thenReturn(Optional.of(category));

        Optional<Category> result = categoryService.getCategoryById(1);

        assertTrue(result.isPresent());
        assertEquals("Stables", result.get().getName());
        verify(repository).findById(1);
    }

    @Test
    public void testCreateCategory() {
        Category category = new Category(1, "stable", "Stables");

        categoryService.createCategory(1, category);

        verify(repository).save(1, category);
    }

    @Test
    public void testCreateCategoryWithNullId() {
        Category category = new Category(1, "stable", "Stables");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            categoryService.createCategory(null, category);
        });

        assertEquals("ID cannot be null", exception.getMessage());
    }

    @Test
    public void testCreateCategoryWithNullCategory() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            categoryService.createCategory(1, null);
        });

        assertEquals("Category cannot be null", exception.getMessage());
    }

    @Test
    public void testUpdateCategory() {
        Category category = new Category(1, "UpdatedCategory", "Update");

        categoryService.updateCategory(1, category);

        verify(repository).update(1, category);
    }

    @Test
    public void testDeleteCategory() {
        categoryService.deleteCategory(1);

        verify(repository).deleteById(1);
    }
}
