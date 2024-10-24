package ru.tbank.edu.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.tbank.edu.model.Location;
import ru.tbank.edu.model.LocationDTO;
import ru.tbank.edu.repository.LocationRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LocationServiceTest {
    @Mock
    private LocationRepository<LocationDTO, Integer> repository;

    @InjectMocks
    private LocationService locationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllLocations() {
        LocationDTO location1 = new LocationDTO(1, "slug1", "Location 1");
        LocationDTO location2 = new LocationDTO(2, "slug2", "Location 2");

        when(repository.findAll()).thenReturn(List.of(location1, location2));

        Collection<LocationDTO> locations = locationService.getAllLocations();

        assertEquals(2, locations.size());
        verify(repository).findAll();
    }

    @Test
    public void testGetLocationByIdFound() {
        LocationDTO location = new LocationDTO(1, "slug1", "Location 1");
        when(repository.findById(1)).thenReturn(Optional.of(location));

        Optional<LocationDTO> found = locationService.getLocationById(1);

        assertTrue(found.isPresent());
        assertEquals(location, found.get());
    }

    @Test
    public void testGetLocationByIdNotFound() {
        when(repository.findById(999)).thenReturn(Optional.empty());

        Optional<LocationDTO> found = locationService.getLocationById(999);

        assertFalse(found.isPresent());
    }

    @Test
    public void testCreateLocation() {
        Location location = new Location("slug1", "Location 1");

        locationService.createLocation(location);

        ArgumentCaptor<LocationDTO> captor = ArgumentCaptor.forClass(LocationDTO.class);
        verify(repository).save(eq(1), captor.capture());

        LocationDTO savedLocation = captor.getValue();
        assertEquals("slug1", savedLocation.getSlug());
        assertEquals("Location 1", savedLocation.getName());
    }

    @Test
    public void testCreateLocationNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            locationService.createLocation(null);
        });

        assertEquals("Location cannot be null", exception.getMessage());
    }

    @Test
    public void testUpdateLocation() {
        LocationDTO updatedLocation = new LocationDTO(1, "slug-updated", "Updated Name");

        locationService.updateLocation(1, updatedLocation);

        verify(repository).update(eq(1), eq(updatedLocation));
    }

    @Test
    public void testDeleteLocation() {
        locationService.deleteLocation(1);

        verify(repository).deleteById(eq(1));
    }
}