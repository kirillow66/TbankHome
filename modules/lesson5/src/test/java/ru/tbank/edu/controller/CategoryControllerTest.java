package ru.tbank.edu.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import ru.tbank.edu.model.Category;
import ru.tbank.edu.service.CategoryService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCategories() {
        Category category1 = new Category(1, "Category 1", "Cat1");
        Category category2 = new Category(2, "Category 2", "Cat2");

        when(categoryService.getAllCategories()).thenReturn(Arrays.asList(category1, category2));

        ResponseEntity<Collection<Category>> response = categoryController.getAllCategories();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(categoryService).getAllCategories();
    }

    @Test
    public void testGetCategoryByIdFound() {
        Category category = new Category(1, "Category 1", "Cat1");

        when(categoryService.getCategoryById(1)).thenReturn(Optional.of(category));

        ResponseEntity<Category> response = categoryController.getCategoryById(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(category, response.getBody());
        verify(categoryService).getCategoryById(1);
    }

    @Test
    public void testGetCategoryByIdNotFound() {
        when(categoryService.getCategoryById(999)).thenReturn(Optional.empty());

        ResponseEntity<Category> response = categoryController.getCategoryById(999);

        assertEquals(404, response.getStatusCodeValue());
        verify(categoryService).getCategoryById(999);
    }

    @Test
    public void testCreateCategory() {
        Category category = new Category(1, "New Category", "NC");

        doNothing().when(categoryService).createCategory(any(Integer.class), any(Category.class));

        ResponseEntity<Void> response = categoryController.createCategory(category);

        assertEquals(201, response.getStatusCodeValue());
        verify(categoryService).createCategory(any(Integer.class), eq(category));
    }

    @Test
    public void testUpdateCategory() {
        Category category = new Category(1, "Updated Category", "UC");

        doNothing().when(categoryService).updateCategory(any(Integer.class), any(Category.class));

        ResponseEntity<Void> response = categoryController.updateCategory(1, category);

        assertEquals(204, response.getStatusCodeValue());
        verify(categoryService).updateCategory(eq(1), eq(category));
    }

    @Test
    public void testDeleteCategory() {
        doNothing().when(categoryService).deleteCategory(1);

        ResponseEntity<Void> response = categoryController.deleteCategory(1);

        assertEquals(204, response.getStatusCodeValue());
        verify(categoryService).deleteCategory(1);
    }
}
