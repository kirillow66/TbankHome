package ru.tbank.edu.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import ru.tbank.edu.model.LocationDTO;
import java.util.Collection;
import java.util.Optional;

public class LocationRepositoryTest {
    private LocationRepository<LocationDTO, Integer> repository;

    @BeforeEach
    public void setUp() {
        repository = new LocationRepository<>();
    }

    @Test
    public void testSaveAndFindById() {
        LocationDTO location = new LocationDTO(1, "slug1", "Location 1");
        repository.save(1, location);

        Optional<LocationDTO> found = repository.findById(1);
        assertTrue(found.isPresent());
        assertEquals(location, found.get());
    }

    @Test
    public void testFindAll() {
        LocationDTO location1 = new LocationDTO(1, "slug1", "Location 1");
        LocationDTO location2 = new LocationDTO(2, "slug2", "Location 2");

        repository.save(1, location1);
        repository.save(2, location2);

        Collection<LocationDTO> allLocations = repository.findAll();
        assertEquals(2, allLocations.size());
    }

    @Test
    public void testUpdate() {
        LocationDTO location = new LocationDTO(1, "slug1", "Location 1");
        repository.save(1, location);

        LocationDTO updatedLocation = new LocationDTO(1, "slug1-updated", "Location 1 Updated");
        repository.update(1, updatedLocation);

        Optional<LocationDTO> found = repository.findById(1);
        assertTrue(found.isPresent());
        assertEquals(updatedLocation, found.get());
    }

    @Test
    public void testDeleteById() {
        LocationDTO location = new LocationDTO(1, "slug1", "Location 1");
        repository.save(1, location);

        repository.deleteById(1);

        Optional<LocationDTO> found = repository.findById(1);
        assertFalse(found.isPresent());
    }

    @Test
    public void testFindByIdNotFound() {
        Optional<LocationDTO> found = repository.findById(999);
        assertFalse(found.isPresent());
    }
}
