package ru.tbank.edu.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.tbank.edu.model.Category;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Category category;

    @BeforeEach
    public void setUp() {
        category = new Category();
        category.setId(1);
        category.setSlug("example-slug");
        category.setName("Example Category");
    }

    @Test
    public void shouldCreateCategory() throws Exception {
        mockMvc.perform(post("/api/v1/places/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(category)))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldGetCategoryById() throws Exception {
        mockMvc.perform(get("/api/v1/places/categories/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Example Category"));
    }

    @Test
    public void shouldUpdateCategory() throws Exception {
        mockMvc.perform(post("/api/v1/places/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(category)))
                .andExpect(status().isCreated());
        category.setName("Updated Category");

        mockMvc.perform(put("/api/v1/places/categories/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(category)))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/v1/places/categories/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Category"));
    }

    @Test
    public void shouldDeleteCategory() throws Exception {
        mockMvc.perform(post("/api/v1/places/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(category)))
                .andExpect(status().isCreated());
        mockMvc.perform(delete("/api/v1/places/categories/1"))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/v1/places/categories/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnNotFoundForNonExistingCategory() throws Exception {
        mockMvc.perform(get("/api/v1/places/categories/999"))
                .andExpect(status().isNotFound());
    }
}
