package ru.tbank.edu.repository;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import java.util.Collection;
import java.util.Optional;

public class RepositoryTest {
    private Repository<String, Integer> repository;

    @BeforeEach
    public void setUp() {
        repository = new Repository<>();
    }

    @Test
    public void testSaveAndFindById() {
        String entity = "Test Entity";
        repository.save(1, entity);

        Optional<String> found = repository.findById(1);
        assertTrue(found.isPresent());
        assertEquals(entity, found.get());
    }

    @Test
    public void testFindByIdNotFound() {
        Optional<String> found = repository.findById(999);
        assertFalse(found.isPresent());
    }

    @Test
    public void testFindAllWhenEmpty() {
        Collection<String> allEntities = repository.findAll();
        assertTrue(allEntities.isEmpty());
    }

    @Test
    public void testFindAllWithEntities() {
        repository.save(1, "Entity 1");
        repository.save(2, "Entity 2");

        Collection<String> allEntities = repository.findAll();
        assertEquals(2, allEntities.size());
    }

    @Test
    public void testUpdateExistingEntity() {
        String initialEntity = "Initial Entity";
        repository.save(1, initialEntity);

        String updatedEntity = "Updated Entity";
        repository.update(1, updatedEntity);

        Optional<String> found = repository.findById(1);
        assertTrue(found.isPresent());
        assertEquals(updatedEntity, found.get());
    }

    @Test
    public void testUpdateNonExistingEntity() {
        String nonExistingEntity = "Non-Existing Entity";

        repository.update(999, nonExistingEntity);

        Optional<String> found = repository.findById(999);
        assertFalse(found.isPresent());
    }

    @Test
    public void testDeleteById() {
        String entity = "Entity to Delete";
        repository.save(1, entity);

        repository.deleteById(1);

        Optional<String> found = repository.findById(1);
        assertFalse(found.isPresent());
    }

    @Test
    public void testDeleteNonExistingId() {
        assertDoesNotThrow(() -> repository.deleteById(999));

        Optional<String> found = repository.findById(999);
        assertFalse(found.isPresent());
    }
}
