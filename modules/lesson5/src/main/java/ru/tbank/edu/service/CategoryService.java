package ru.tbank.edu.service;

import org.springframework.stereotype.Service;
import ru.tbank.edu.aop.LogExecutionTime;
import ru.tbank.edu.model.Category;
import ru.tbank.edu.repository.Repository;

import java.util.Collection;
import java.util.Optional;
@LogExecutionTime
@Service
public class CategoryService {
    private final Repository<Category, Integer> repository;

    public CategoryService(Repository<Category, Integer> repository) {
        this.repository = repository;
    }

    public Collection<Category> getAllCategories() {
        return repository.findAll();
    }

    public Optional<Category> getCategoryById(Integer id) {
        return repository.findById(id);
    }

    public void createCategory(Integer id, Category category) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        repository.save(id, category);
    }

    public void updateCategory(Integer id, Category category) {
        repository.update(id, category);
    }

    public void deleteCategory(Integer id) {
        repository.deleteById(id);
    }
}
