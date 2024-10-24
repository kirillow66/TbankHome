package ru.tbank.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tbank.edu.aop.LogExecutionTime;
import ru.tbank.edu.model.Category;
import ru.tbank.edu.service.CategoryService;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/places/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @LogExecutionTime
    @GetMapping
    public ResponseEntity<Collection<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @LogExecutionTime
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Integer id) {
        return categoryService.getCategoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @LogExecutionTime
    @PostMapping
    public ResponseEntity<Void> createCategory(@RequestBody Category category) {
        categoryService.createCategory(category.getId(), category);
        return ResponseEntity.created(null).build();
    }

    @LogExecutionTime
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCategory(@PathVariable Integer id, @RequestBody Category category) {
        categoryService.updateCategory(id, category);
        return ResponseEntity.noContent().build();
    }

    @LogExecutionTime
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
